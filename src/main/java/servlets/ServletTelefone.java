package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOLoginRepository;
import dao.DAOTelefoneRepository;
import model.ModelTelefone;

@WebServlet("/ServletTelefone")
public class ServletTelefone extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;

	public ServletTelefone() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String idUser = request.getParameter("idUser");
		String idTel = request.getParameter("idTel");
		String id = request.getParameter("Id");
		String numero=request.getParameter("Numero");

		if (acao != null && idUser != null && idUser != "" && !acao.isEmpty() && acao.equals("buscarUser")) {

			DAOTelefoneRepository daoModelLogin = new DAOTelefoneRepository();
			DAOLoginRepository dao = new DAOLoginRepository();
			ModelTelefone modelLogin = new ModelTelefone();
			List<ModelTelefone> modelLogins = new ArrayList<ModelTelefone>();

			try {
				modelLogin = daoModelLogin.FoneId(idUser);
				modelLogins = daoModelLogin.listFones(Long.parseLong(idUser));
				modelLogin.setUsuario_cad_id(dao.pesquisarId(String.valueOf(super.getUserLogado(request))));
				modelLogin.setUsuario_pai_id(dao.pesquisarId(idUser));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.setAttribute("modelLogin", modelLogin);
			request.setAttribute("modelLogins", modelLogins);
			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/telefone.jsp");
			redirecionar.forward(request, response);

		} else if (acao != null && !acao.isEmpty() && numero != null && !numero.isEmpty() && acao.equals("deletar")) {
			
			

			DAOTelefoneRepository daoTelefone = new DAOTelefoneRepository();	
			DAOTelefoneRepository daoModelLogin = new DAOTelefoneRepository();
			DAOLoginRepository dao = new DAOLoginRepository();
			ModelTelefone modelLogin = new ModelTelefone();
			List<ModelTelefone> modelLogins = new ArrayList<ModelTelefone>();
			
			
			try {
				daoTelefone.deletarTelefone(numero);
				modelLogin = daoModelLogin.FoneId(id);
				modelLogins = daoModelLogin.listFones(Long.parseLong(id));
				modelLogin.setUsuario_cad_id(dao.pesquisarId(String.valueOf(super.getUserLogado(request))));
				modelLogin.setUsuario_pai_id(dao.pesquisarId(id));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("modelLogin", modelLogin);
			request.setAttribute("modelLogins", modelLogins);
			request.setAttribute("msg", "Excluido com sucesso!!");
			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/telefone.jsp");
			redirecionar.forward(request, response);

			
			
		
			

		} else if (acao != null && !acao.isEmpty() && idTel != null && !idTel.isEmpty()
				&& acao.equals("editarTelefone")) {

			DAOTelefoneRepository daoModelLogin = new DAOTelefoneRepository();
			DAOLoginRepository dao = new DAOLoginRepository();
			ModelTelefone modelLogin = new ModelTelefone();
			List<ModelTelefone> modelLogins = new ArrayList<ModelTelefone>();

			try {
				modelLogins = daoModelLogin.listFones(Long.parseLong(idUser));
				modelLogin.setUsuario_cad_id(dao.pesquisarId(String.valueOf(super.getUserLogado(request))));
				modelLogin.setUsuario_pai_id(dao.pesquisarId(idUser));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.setAttribute("modelLogin", modelLogin);
			request.setAttribute("modelLogins", modelLogins);
			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/telefone.jsp");
			redirecionar.forward(request, response);

		} else {
			// RequestDispatcher redirecionar
			// =request.getRequestDispatcher("principal/user.jsp");
			// redirecionar.forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String id = request.getParameter("Id");
			String numero = request.getParameter("Numero");
			numero=numero.replaceAll("\\D", "");
			String nuAnt = request.getParameter("nuAnt");
			//buscar os dados do pai
			DAOLoginRepository dao2 = new DAOLoginRepository();
			//fazer o registro na tabela telefone
			DAOTelefoneRepository daoTelefone = new DAOTelefoneRepository();

			ModelTelefone modelTelefone = new ModelTelefone();
			//lista de telefones
			List<ModelTelefone> modelLogins = new ArrayList<ModelTelefone>();
			
			if (daoTelefone.isNew(nuAnt)) {
				modelTelefone.setNumero(numero);
				try {
					modelTelefone.setUsuario_cad_id(dao2.buscarId(String.valueOf(super.getUserLogado(request))));
					modelTelefone.setUsuario_pai_id(dao2.buscarId(id));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					daoTelefone.gravarTelefone(modelTelefone);
					modelLogins = daoTelefone.listFones(Long.parseLong(id));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				request.setAttribute("msg", "Registro salvo!!!");

				request.setAttribute("modelLogin", modelTelefone);
				request.setAttribute("modelLogins", modelLogins);
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/telefone.jsp");
				redirecionar.forward(request, response);
			} else {
				//metodo de update do telefone

				modelTelefone.setNumero(numero);
				try {
					modelTelefone.setUsuario_cad_id(dao2.buscarId(String.valueOf(super.getUserLogado(request))));
					modelTelefone.setUsuario_pai_id(dao2.buscarId(id));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					daoTelefone.updateTelefone(modelTelefone,nuAnt);
					modelLogins = daoTelefone.listFones(Long.parseLong(id));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				request.setAttribute("msg", "Registro atualizado!!!");

				request.setAttribute("modelLogin", modelTelefone);
				request.setAttribute("modelLogins", modelLogins);
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/telefone.jsp");
				redirecionar.forward(request, response);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
