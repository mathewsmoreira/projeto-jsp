package filter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.flywaydb.core.Flyway;

import connection.SingleConnectionBanco;

@WebFilter(urlPatterns = { "/principal/*" })
//urlpatterns e usando quando se quer passar mais de uma url tanto para o servlet
//quanto para o filter
/*
 * Na nota��o webfilter tem que passar o endere�o da url que o filtro ira
 * monitorar, sendo assim toda requisi��o que passar pelos arquivos dessa pasta
 * ser�o monitoradas pelo filter
 */
public class FilterAutenticacao extends HttpFilter implements Filter {
	
	private static String url="jdbc:postgresql://db:5432/projeto_jsp?autoReconnect=true";
	//private static String url = "jdbc:postgresql://localhost:5432/curso_jsp";
	private static Connection connection;
	private static final long serialVersionUID = 1L;

	public FilterAutenticacao() {
		super();

	}

	// encera os processos quando o servidor e parado
	// encerar a conex�o com o banco de dados
	public void destroy() {
		
        HttpServletRequest request = (HttpServletRequest) getServletContext().getAttribute("request");
        HttpSession session = request.getSession();
        session.removeAttribute("login");
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	public void destroy(HttpServletRequest request) {
		
        HttpSession session = request.getSession();
        session.removeAttribute("login");
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	// intercepta todas as requisi��es que vem do projeto
	// e da resposta para o cliente
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {

			// o request que vem como paramento e quase a mesma coisa do HttpServletRequest
			// a diferen�a e que o http vai ter alguns metodos a mais
			// porem a sua estrutura e muito igual

			// pra fazer a autenca��o precisamos colocar os paramentros de login
			// e senha em session pois esse objeto pode ser acessado de qualquer
			// local do projeto, para isso precisamos do http.
			ServletContext context = request.getServletContext();
			context.setAttribute("request", request);

			HttpServletRequest req = (HttpServletRequest) request;

			HttpSession session = req.getSession();

			String login = (String) session.getAttribute("login");

			// esse metodo vai trazer a url que o usuario esta tentando acessar
			// e bom colocar no html uma tag com a url para que possa ser acessado
			// pela servlet
			String urlParaAutenticar = req.getServletPath();

			// valida��o para saber se esta logado, sen�o estiver ele vai redirecionar

			if (login == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {

				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Informe o Login e Senha!!!");
				redireciona.forward(request, response);
				return;// tem que usar o return para ele para a requisi��o e retornar pro login

			} else {
				chain.doFilter(request, response);
			}

		} catch (Exception e) {
			
			RequestDispatcher redirecionar =request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			
			e.printStackTrace();

			// caso de alguma exece��o no dofilter iremos fazer um rollback
			// no banco
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	// inicia os recursos quando o servidor e sobe o projeto
	// ex iniciar a conex�o com o banco de dados
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		
		Flyway flyway = Flyway.configure()
			    .baselineOnMigrate(true) // Configuração adicional para criar a tabela de histórico
			    .dataSource(url,"postgres","pp2mipx9a")
			    .load();
		flyway.baseline(); 
		flyway.migrate();
        
		
		connection = SingleConnectionBanco.getConnection();
	}

}
