package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	private static String url="jdbc:postgresql://db:5432/projeto_jsp?autoReconnect=true";
	//private static String url = "jdbc:postgresql://localhost:5432/curso_jsp";
	private static String password="pp2mipx9a";
	private static String user="postgres";
	private static Connection connection = null;
	
	
	
	public SingleConnectionBanco() {
		connectar();
	}
	
	static{
		connectar();
	}
	
	private static void connectar() {
		try {
			if(connection==null) {
				Class.forName("org.postgresql.Driver");
			connection=DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(false);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}
	
	
	

}
