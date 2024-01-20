package servlets;

import java.io.Serializable;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.DAOLoginRepository;

public class ServletGenericUtil extends HttpServlet implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DAOLoginRepository dao = new DAOLoginRepository();
	
	
	
	public long getUserLogado(HttpServletRequest request) throws SQLException {
		
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("login");
		
		return dao.pesquisarSession(login).getId();
		
	}

}
