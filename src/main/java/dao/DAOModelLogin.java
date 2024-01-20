package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLoginServlet;

public class DAOModelLogin {
	private static Connection connection;
	
	
	
	public DAOModelLogin() {
		connection=SingleConnectionBanco.getConnection();	
		
	}
	
	
	//metodo responsavel por validar se o login e a senha estar no banco de dados.
	
	public boolean autenticar(ModelLoginServlet model) throws SQLException {
		
		String sql = "select * from model_login where upper(login) = upper(?) and senha = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, model.getLogin());
		statement.setString(2, model.getSenha());
		ResultSet result = statement.executeQuery();
		
		while(result.next()) {
			return true;
		}
		
		return false;
	}
	

}
