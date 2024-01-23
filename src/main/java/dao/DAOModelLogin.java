package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLoginServlet;

public class DAOModelLogin {
	Connection connection;
	
	
	
	public DAOModelLogin() {
		connection=SingleConnectionBanco.getConnection();	
		
	}
	
	
	//metodo responsavel por validar se o login e a senha estar no banco de dados.
	
	public boolean autenticar(ModelLoginServlet model) throws SQLException {
		
		String sql = "select * from model_login where upper(login) = upper(?) and senha = ?";
		try {
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, model.getLogin());
		statement.setString(2, model.getSenha());
		ResultSet result = statement.executeQuery();
		
		while(result.next()) {
			return true;
		}
		}catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}
		return false;
		
	}
	
	public boolean registroTelefone() throws SQLException {
		String sql = "select exists(select 1 from telefone where usuario_pai_id >4)";
		try {
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet rSet = statement.executeQuery();
		boolean retorno = rSet.next();
		
		return retorno;
		}catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}
		return false;
	}
	
	public void limparCache() throws SQLException {
		try {
		if(registroTelefone()) {
		String sql1 ="delete from telefone where usuario_pai_id > 4;";
		PreparedStatement statement1 = connection.prepareStatement(sql1);
		statement1.executeUpdate();
		connection.commit();
			
		String sql2 = "delete from model_login where usuario_id <> 1;";
		PreparedStatement statement2 = connection.prepareStatement(sql2);
		statement2.executeUpdate();
		connection.commit();
		}else {
			String sql2 = "delete from model_login where usuario_id <> 1;";
			PreparedStatement statement2 = connection.prepareStatement(sql2);
			statement2.execute();
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
