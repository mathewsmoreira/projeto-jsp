package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beanDto.BeanGrafico;
import dao.DAOLoginRepository;
import model.ModelLoginServlet;
import util.ReportUtil;

@MultipartConfig
@WebServlet("/ServletUsuarioController")
public class ServletUsuarioController extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;
       
    public ServletUsuarioController() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		String acao = request.getParameter("acao");
		
		if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
			String idUser=request.getParameter("Id");
			DAOLoginRepository daoLoginRepository = new DAOLoginRepository();
			
			
			try {
				daoLoginRepository.deletar(idUser);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("msg", "O registro não foi deletado, tentar novamente!");
			}
			
			request.setAttribute("msg", "Excluido com sucesso!!");
			DAOLoginRepository daoModelLogin = new DAOLoginRepository();
			List<ModelLoginServlet>	modelLogins = new ArrayList<ModelLoginServlet>();
			
			modelLogins=daoModelLogin.buscarUsuarios(super.getUserLogado(request));
			request.setAttribute("modelLogins", modelLogins);
			request.setAttribute("totalPagina", daoModelLogin.totalPagina(super.getUserLogado(request)));
			RequestDispatcher redirecionar =request.getRequestDispatcher("principal/user.jsp");
			redirecionar.forward(request, response);
			
		}else if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarAjax")) {
			String idUser=request.getParameter("Id");
			DAOLoginRepository daoLoginRepository = new DAOLoginRepository();
			
			
			try {
				daoLoginRepository.deletar(idUser);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("msg", "O registro não foi salvo, por favor revisar os dados!!");
				
			}
			
			
			response.getWriter().write("Excluido com sucesso!!");
			
		}else if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("pesquisarAjax")){
			String User=request.getParameter("nome");
			
			DAOLoginRepository dao2 = new DAOLoginRepository();
			List<ModelLoginServlet> nomes = new ArrayList<ModelLoginServlet>();
			
			nomes=dao2.buscarNome(User,super.getUserLogado(request));
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonUsers = gson.toJson(nomes);
			String totalPaginas = ""+dao2.buscarNomePg(User, super.getUserLogado(request));
			System.out.println(totalPaginas);
			response.addHeader("totalPaginas", totalPaginas);
			
			response.getWriter().write(jsonUsers);
			
		}else if (acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("paginarModal")){
			String User=request.getParameter("nome");
			String offset=request.getParameter("pagina");
			
			DAOLoginRepository dao2 = new DAOLoginRepository();
			List<ModelLoginServlet> nomes = new ArrayList<ModelLoginServlet>();
			
			nomes=dao2.buscarNomePaginado(User,super.getUserLogado(request),offset);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonUsers = gson.toJson(nomes);
			String totalPaginas = ""+dao2.buscarNomePg(User, super.getUserLogado(request));
			System.out.println(totalPaginas);
			response.addHeader("totalPaginas", totalPaginas);
			
			response.getWriter().write(jsonUsers);
			
			
			
			
		}else if (acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("relatorioUser")){
			
			String dataInicial = request.getParameter("dataInicial");
			String dataFinal = request.getParameter("dataFinal");

			
			DAOLoginRepository dao = new DAOLoginRepository();
			List<ModelLoginServlet> modelLogins = new ArrayList<ModelLoginServlet>();
			
			
			if(dataInicial == null || dataInicial.isEmpty() || dataFinal == null || dataFinal.isEmpty() ) {
				modelLogins = dao.buscarTodosUsuarios(super.getUserLogado(request));
				
			}else {
				java.sql.Date dataI = java.sql.Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataInicial)));
				java.sql.Date dataF = java.sql.Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataFinal)));
				modelLogins = dao.filtrarData(super.getUserLogado(request), dataI, dataF);
			}
				
			ReportUtil report = new ReportUtil();
			byte [] rpt = report.geraRelatorioPDF(modelLogins, "rel-user", request.getServletContext());
			
			response.setHeader("content-disposition", "attachment;filename=arquivo.pdf");
			response.getOutputStream().write(rpt);

			
			
		}else if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("relatorioUserXls")) {
			String dataInicial = request.getParameter("dataInicial");
			String dataFinal = request.getParameter("dataFinal");

			
			DAOLoginRepository dao = new DAOLoginRepository();
			List<ModelLoginServlet> modelLogins = new ArrayList<ModelLoginServlet>();
			
			
			if(dataInicial == null || dataInicial.isEmpty() || dataFinal == null || dataFinal.isEmpty() ) {
				modelLogins = dao.buscarTodosUsuarios(super.getUserLogado(request));
				
			}else {
				
				java.sql.Date dataI = new java.sql.Date(new SimpleDateFormat("dd/mm/yyyy").parse(dataInicial).getTime());
				java.sql.Date dataF = new java.sql.Date(new SimpleDateFormat("dd/mm/yyyy").parse(dataFinal).getTime());
				modelLogins = dao.filtrarData(super.getUserLogado(request), dataI, dataF);
			}
				
			ReportUtil report = new ReportUtil();
			byte [] rpt = report.geraRelatorioXLS(modelLogins);
			
			response.setHeader("content-disposition", "attachment;filename=arquivo.xlsx");
			response.getOutputStream().write(rpt);
			
			
		}else  if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarId")) {
			String id = request.getParameter("id");
			
			DAOLoginRepository daoModelLogin = new DAOLoginRepository();
			ModelLoginServlet modelLoginServlet = new ModelLoginServlet();
			
			List<ModelLoginServlet>	modelLogins = new ArrayList<ModelLoginServlet>();
			modelLoginServlet=daoModelLogin.pesquisarId(id);
			modelLogins=daoModelLogin.buscarUsuarios(super.getUserLogado(request));
			request.setAttribute("modelLogins", modelLogins);
			
			request.setAttribute("modelLogin", modelLoginServlet);
			request.setAttribute("msg", "Registro pronto pra edição!!");
			request.setAttribute("totalPagina", daoModelLogin.totalPagina(super.getUserLogado(request)));
			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/user.jsp");
			redirecionar.forward(request, response);
					
		}else if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("gerarGrafico")) {
			
			BeanGrafico beanGrafico = new BeanGrafico();
			DAOLoginRepository dao2 = new DAOLoginRepository();
			beanGrafico=dao2.buscarUsuariosAdmin();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(beanGrafico); 
			response.getWriter().write(json);

			
			
			
		}else if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar")) {
			int pagina = Integer.parseInt(request.getParameter("pagina"));
			
			DAOLoginRepository daoModelLogin = new DAOLoginRepository();
			List<ModelLoginServlet>	modelLogins = new ArrayList<ModelLoginServlet>();
			
			modelLogins=daoModelLogin.buscarUsuariosPaginados(super.getUserLogado(request),pagina);
			request.setAttribute("modelLogins", modelLogins);
			request.setAttribute("totalPagina", daoModelLogin.totalPagina(super.getUserLogado(request)));
			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/searchUser.jsp");
			redirecionar.forward(request, response);			
			
		}else if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
			
			DAOLoginRepository daoModelLogin = new DAOLoginRepository();
			List<ModelLoginServlet>	modelLogins = new ArrayList<ModelLoginServlet>();
			
			modelLogins=daoModelLogin.buscarUsuarios(super.getUserLogado(request));
			request.setAttribute("modelLogins", modelLogins);
			request.setAttribute("totalPagina", daoModelLogin.totalPagina(super.getUserLogado(request)));
			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/searchUser.jsp");
			redirecionar.forward(request, response);				
			
		}else if(acao != null && !acao.isBlank() && acao.equalsIgnoreCase("downloadFoto")) {
			String id = request.getParameter("id");
			DAOLoginRepository dao = new DAOLoginRepository();
			ModelLoginServlet model = dao.pesquisarId(id);
			if(model.getFotoUser() !=null && !model.getFotoUser().isEmpty()) {
			//tem que setar um header no reponse e colocar os parametros abaixo
			//para que ele reconhe�a que e um donwnload que o usuario esta solicitando
			//nao se esquecer de no final da string colocar a extens�o do arqvuivo
			//o attachment filename ja e o proprio nome do arquivo com a extens�o para ser feito o download no navegador
			//pelo usuario tem que coloc nomeDoArquivo.extensaoSalvaNoBanco
				response.setHeader("content-disposition", "attachment;filename=arquivo." + model.getFotoExtensao());
				response.getOutputStream().write(new Base64().decodeBase64(model.getFotoUser().split("\\,")[1]));
				
				
				
			}
			
			
			
		}else if(acao ==null || acao.isEmpty()) {
			RequestDispatcher redirecionar =request.getRequestDispatcher("principal/user.jsp");
			redirecionar.forward(request, response);
		}
		else {
			DAOLoginRepository daoModelLogin = new DAOLoginRepository();
			List<ModelLoginServlet>	modelLogins = new ArrayList<ModelLoginServlet>();
			
			modelLogins=daoModelLogin.buscarUsuarios(super.getUserLogado(request));
			request.setAttribute("modelLogins", modelLogins);
			request.setAttribute("totalPagina", daoModelLogin.totalPagina(super.getUserLogado(request)));
			RequestDispatcher redirecionar =request.getRequestDispatcher("principal/user.jsp");
			redirecionar.forward(request, response);
			
		}
				

		
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar =request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		DAOLoginRepository daoLoginRepository = new DAOLoginRepository();
		
		String id = request.getParameter("Id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String sexo = request.getParameter("sexoUser");
		String cep = request.getParameter("cep");
		String logradouro = request.getParameter("logradouro");
		String bairro = request.getParameter("bairro");
		String localidade = request.getParameter("localidade");
		String uf = request.getParameter("uf");
		String numero = request.getParameter("numero");
		String complemento = request.getParameter("complemento");
		String rendamensal = request.getParameter("rendaMensal");
		
		ModelLoginServlet modelLoginServlet = new ModelLoginServlet();
		java.sql.Date datanascimento = java.sql.Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(request.getParameter("dataNascimento"))));
		double renda = Double.parseDouble(rendamensal.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", "."));
		
		
		//prestar aten�ao no ID tem que ser um long para adicionarmos no banco
		// e o parametro encaminhado pelo http vem em um string
		//temos que usar o Objeto Long em vez  do tipo primitivo long
		//caso seja usado o tipo primitivo ele vai dar uma null point exception
		//por isso tem que usar o objeto Long para fazer o parse para o tipo primitivo.
		
		modelLoginServlet.setDatanascimento(datanascimento);
		modelLoginServlet.setRendamensal(renda);
		modelLoginServlet.setId(id !=null && !id.isEmpty() ? Long.parseLong(id) : null);
		modelLoginServlet.setNome(nome);
		modelLoginServlet.setEmail(email);
		modelLoginServlet.setLogin(login);
		modelLoginServlet.setSenha(senha);
		modelLoginServlet.setSexo(sexo);
		modelLoginServlet.setCep(cep);
		modelLoginServlet.setLogradouro(logradouro);
		modelLoginServlet.setBairro(bairro);
		modelLoginServlet.setLocalidade(localidade);
		modelLoginServlet.setUf(uf);
		modelLoginServlet.setNumero(Integer.valueOf(numero));
		modelLoginServlet.setComplemento(complemento);
		
		//parte de captura da imagem do usuario, depois e transformada em base 64 para salvar no banco
		
		//primeiro tem que fazer o teste para saber se o formulario e multipartcontent, caso contrario nao ira funcionar
		if(ServletFileUpload.isMultipartContent(request)) {
			
			//realiza o teste para ver se o size do part e maior que zero
			//essa condi��o vai possibilitar que o usuario atualizei outros dados sem trocar a foto
			Part part = request.getPart("imgFile");
			if(part.getSize() >0) {
				
				//tem que transforma para byte
				//no jakarta ja tem o IOUtils porem na servlet tem que usar o apache commons io
				byte[] foto = org.apache.commons.io.IOUtils.toByteArray(part.getInputStream());
				//convertendo o byte para base64 para salvar no banco
				//foi usado a classe do tomcat.util.codec
				//tem que concatenar a string 64 com o seguinte texto "data:/image/extensao do arquivo;base64,"
				String imagemBase64 = "data:"+part.getContentType()+";base64,"+new Base64().encodeBase64String(foto);				
				String extensaoFoto = part.getContentType().split("\\/")[1];
				
				modelLoginServlet.setFotoUser(imagemBase64);
				modelLoginServlet.setFotoExtensao(extensaoFoto);
			}
			
			
		}
		
		
		if(daoLoginRepository.isNew(modelLoginServlet.getLogin())) {
			
			//modelLoginServlet=daoLoginRepository.pesquisar(login);
			modelLoginServlet=daoLoginRepository.gravar(modelLoginServlet,super.getUserLogado(request));
			request.setAttribute("msg", "Registro atualizado!!!");
			request.setAttribute("modelLogin", modelLoginServlet);
			DAOLoginRepository daoModelLogin = new DAOLoginRepository();
			List<ModelLoginServlet>	modelLogins = new ArrayList<ModelLoginServlet>();
			
			modelLogins=daoModelLogin.buscarUsuarios(super.getUserLogado(request));
			request.setAttribute("modelLogins", modelLogins);					
			
			request.setAttribute("totalPagina", daoModelLogin.totalPagina(super.getUserLogado(request)));
			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/user.jsp");
			redirecionar.forward(request, response);
			
		}else {
			modelLoginServlet=daoLoginRepository.gravar(modelLoginServlet,super.getUserLogado(request));
			try {
			DAOLoginRepository daoModelLogin = new DAOLoginRepository();
			List<ModelLoginServlet>	modelLogins = new ArrayList<ModelLoginServlet>();
			
			modelLogins=daoModelLogin.buscarUsuarios(super.getUserLogado(request));
			request.setAttribute("modelLogins", modelLogins);					
			request.setAttribute("modelLogin", modelLoginServlet);
			request.setAttribute("totalPagina", daoModelLogin.totalPagina(super.getUserLogado(request)));
			request.setAttribute("msg", "Registro criado com sucesso!!");
			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/user.jsp");
			redirecionar.forward(request, response);
			}catch (Exception e) {
				e.printStackTrace();
				DAOLoginRepository daoModelLogin = new DAOLoginRepository();
				List<ModelLoginServlet>	modelLogins = new ArrayList<ModelLoginServlet>();
				modelLogins=daoModelLogin.buscarUsuarios(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);					
				request.setAttribute("modelLogin", modelLoginServlet);
				request.setAttribute("totalPagina", daoModelLogin.totalPagina(super.getUserLogado(request)));
				request.setAttribute("msg", "O registro não foi salvo, por favor revise os dados!");
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/user.jsp");
				redirecionar.forward(request, response);
			}
			
		}

		

		
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar =request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
		
		
		
		
		
	}

}
