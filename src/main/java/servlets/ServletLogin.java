package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOLoginRepository;
import dao.DAOModelLogin;
import model.ModelLoginServlet;

@WebServlet(urlPatterns = { "/principal/ServletLogin", "/ServletLogin" }) /* Mapeamento de URL da servlet */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOLoginRepository daoRepository;

	public ServletLogin() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String logout =request.getParameter("logout");

		
		if(logout !=null && !logout.isEmpty() && logout.equalsIgnoreCase("true")) {
			request.getSession().invalidate();
			RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
			redirecionar.forward(request, response);
			
		}else {
		
			doPost(request, response);
		}
			
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		try {
			if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
				ModelLoginServlet modelLogin = new ModelLoginServlet();
				modelLogin.setLogin(login);
				modelLogin.setSenha(senha);
				DAOModelLogin daoModelLogin = new DAOModelLogin();
				daoRepository = new DAOLoginRepository();

				if (daoModelLogin.autenticar(modelLogin)) {
					ModelLoginServlet modelLoginAdm = new ModelLoginServlet();
					modelLoginAdm= daoRepository.pesquisarSession(modelLogin.getLogin());
					request.getSession().setAttribute("login", modelLogin.getLogin());
					request.getSession().setAttribute("isAdmin", modelLoginAdm.isUsuarioadmin());
					
					request.getSession().setAttribute("fotoUser", modelLoginAdm.getFotoUser());

					if (url == null || url.equals("null")) {
						url = "/principal/principal.jsp";
					}

					RequestDispatcher req = request.getRequestDispatcher(url);
					req.forward(request, response);

				} else {

					// quando adiciona o / na frente do mapeamento estamos informando que
					// e pra voltar pra pagina pasta anterior
					RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");

					// para mandar uma mensagem ou alguma informa��o do backend para o frontend
					// usa-se o metodo setAtribute do request
					// ir� ser passado o nome de uma key e logo depois o valor

					request.setAttribute("msg", "Informe o Login e Senha!!!");
					redirecionar.forward(request, response);

				}

			} else {
				// o requestDispatcher vai usar o metodo get do request e colocar o mapeamento
				// do
				// index.jsp

				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");

				// para mandar uma mensagem ou alguma informa��o do backend para o frontend
				// usa-se o metodo setAtribute do request
				// ir� ser passado o nome de uma key e logo depois o valor

				request.setAttribute("msg", "Informe o Login e Senha!!!");
				redirecionar.forward(request, response);

			}
		} catch (Exception e) {
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);

			e.printStackTrace();
		}
	}

}
