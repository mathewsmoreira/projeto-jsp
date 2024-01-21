package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import beanDto.BeanGrafico;
import connection.SingleConnectionBanco;
import model.ModelLoginServlet;

public class DAOLoginRepository {

	Connection connection;
	
	public DAOLoginRepository() {
		
		connection=SingleConnectionBanco.getConnection();
		
	}
	
	
	public ModelLoginServlet gravar(ModelLoginServlet modelLoginServlet, long id) throws SQLException {
		
		if(modelLoginServlet.isNew()) {
			String sql="INSERT INTO model_login(login, senha, nome, "
					+ "email, usuario_id ,sexo, cep, logradouro, bairro,"
					+ "localidade, uf, numero, complemento,datanascimento,rendamensal)VALUES (?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, ?, ?, ?,?,?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, modelLoginServlet.getLogin());
			statement.setString(2, modelLoginServlet.getSenha());
			statement.setString(3, modelLoginServlet.getNome());
			statement.setString(4, modelLoginServlet.getEmail());
			statement.setLong(5, id);
			statement.setString(6, modelLoginServlet.getSexo());
			statement.setString(7, modelLoginServlet.getCep());
			statement.setString(8, modelLoginServlet.getLogradouro());
			statement.setString(9, modelLoginServlet.getBairro());
			statement.setString(10, modelLoginServlet.getLocalidade());
			statement.setString(11, modelLoginServlet.getUf());
			statement.setInt(12, modelLoginServlet.getNumero());
			statement.setString(13, modelLoginServlet.getComplemento());
			statement.setDate(14, modelLoginServlet.getDatanascimento());
			statement.setDouble(15, modelLoginServlet.getRendamensal());
			
			
			
			statement.execute();
			connection.commit();
			
			if(modelLoginServlet.getFotoUser() !=null && !modelLoginServlet.getFotoUser().isEmpty()) {
				
				sql="update model_login set fotoUser = ?, fotoext=? where login=?";
				statement = connection.prepareStatement(sql);
				statement.setString(1, modelLoginServlet.getFotoUser());
				statement.setString(2, modelLoginServlet.getFotoExtensao());
				statement.setString(3, modelLoginServlet.getLogin());
				statement.execute();
				connection.commit();
				
			}
			
			return this.pesquisar(modelLoginServlet.getLogin());
		
		}else {
			String sqlUpdate = "UPDATE model_login SET login=?, senha=?, "
					+ "id=?, nome=?, email=?, sexo=?, cep=?, logradouro=?,"
					+ "bairro=?, localidade=?, uf=?, numero=?, "
					+ "complemento=?, datanascimento=?,rendamensal=? WHERE login =?;";
			
			PreparedStatement prepareSql= connection.prepareStatement(sqlUpdate);
			prepareSql.setString(1, modelLoginServlet.getLogin());
			prepareSql.setString(2, modelLoginServlet.getSenha());
			prepareSql.setLong(3, modelLoginServlet.getId());
			prepareSql.setString(4, modelLoginServlet.getNome());
			prepareSql.setString(5, modelLoginServlet.getEmail());
			prepareSql.setString(6, modelLoginServlet.getSexo());
			
			prepareSql.setString(7, modelLoginServlet.getCep());
			prepareSql.setString(8, modelLoginServlet.getLogradouro());
			prepareSql.setString(9, modelLoginServlet.getBairro());
			prepareSql.setString(10, modelLoginServlet.getLocalidade());
			prepareSql.setString(11, modelLoginServlet.getUf());
			prepareSql.setInt(12, modelLoginServlet.getNumero());
			prepareSql.setString(13, modelLoginServlet.getComplemento());
			prepareSql.setDate(14, modelLoginServlet.getDatanascimento());
			prepareSql.setDouble(15, modelLoginServlet.getRendamensal());
			
			prepareSql.setString(16,modelLoginServlet.getLogin());

			
			prepareSql.executeUpdate();
			
			connection.commit();
			
			if(modelLoginServlet.getFotoUser() !=null && !modelLoginServlet.getFotoUser().isEmpty()) {
				sqlUpdate = "update model_login set fotoUser = ?, fotoext=? where id=?";
				prepareSql =connection.prepareStatement(sqlUpdate);
				prepareSql.setString(1, modelLoginServlet.getFotoUser());
				prepareSql.setString(2, modelLoginServlet.getFotoExtensao());
				prepareSql.setLong(3, modelLoginServlet.getId());
				
				prepareSql.executeUpdate();
				connection.commit();
				
				
			}
			
			
			return this.pesquisar(modelLoginServlet.getLogin());
			
		}
				
		

	}
	
	public ModelLoginServlet pesquisar(String login) throws SQLException {
		ModelLoginServlet model = new ModelLoginServlet();
		String sql="SELECT * FROM model_login where upper(login) = upper(?) and usuarioadmin = false";		
		PreparedStatement statement= connection.prepareStatement(sql);
		statement.setString(1, login);
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			model.setId(resultado.getLong("id"));
			model.setLogin(resultado.getString("login"));
			model.setSenha(resultado.getString("senha"));
			model.setNome(resultado.getString("nome"));
			model.setEmail(resultado.getString("email"));
			model.setSexo(resultado.getString("sexo"));
			model.setFotoUser(resultado.getString("fotouser"));
			model.setFotoExtensao(resultado.getString("fotoext"));
			model.setCep(resultado.getString("cep"));
			model.setLogradouro(resultado.getString("logradouro"));
			model.setBairro(resultado.getString("bairro"));
			model.setLocalidade(resultado.getString("localidade"));
			model.setUf(resultado.getString("uf"));
			model.setNumero(resultado.getInt("numero"));
			model.setComplemento(resultado.getString("complemento"));
			model.setDatanascimento(resultado.getDate("datanascimento"));
			model.setRendamensal(resultado.getDouble("rendamensal"));
			
			return model;			
		}
		
		
		return model;
	}
	

	
	public ModelLoginServlet pesquisarSession(String login) throws SQLException {
		ModelLoginServlet model = new ModelLoginServlet();
		String sql="SELECT * FROM model_login where upper(login) = upper(?)";		
		PreparedStatement statement= connection.prepareStatement(sql);
		statement.setString(1, login);
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			model.setId(resultado.getLong("id"));
			model.setLogin(resultado.getString("login"));
			model.setSenha(resultado.getString("senha"));
			model.setNome(resultado.getString("nome"));
			model.setEmail(resultado.getString("email"));
			model.setUsuarioadmin(resultado.getBoolean("usuarioadmin"));
			model.setSexo(resultado.getString("sexo"));
			model.setFotoUser(resultado.getString("fotouser"));
			model.setFotoExtensao(resultado.getString("fotoext"));
			model.setCep(resultado.getString("cep"));
			model.setLogradouro(resultado.getString("logradouro"));
			model.setBairro(resultado.getString("bairro"));
			model.setLocalidade(resultado.getString("localidade"));
			model.setUf(resultado.getString("uf"));
			model.setNumero(resultado.getInt("numero"));
			model.setComplemento(resultado.getString("complemento"));
			model.setDatanascimento(resultado.getDate("datanascimento"));
			model.setRendamensal(resultado.getDouble("rendamensal"));
			
			
			return model;			
		}
		
		
		return model;
	}
	
	public ModelLoginServlet pesquisarId(String id) throws SQLException {
		ModelLoginServlet model = new ModelLoginServlet();
		String sql="SELECT * FROM model_login where id = ? and usuarioadmin =false";		
		PreparedStatement statement= connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			model.setId(resultado.getLong("id"));
			model.setLogin(resultado.getString("login"));
			model.setSenha(resultado.getString("senha"));
			model.setNome(resultado.getString("nome"));
			model.setEmail(resultado.getString("email"));
			model.setSexo(resultado.getString("sexo"));
			model.setFotoUser(resultado.getString("fotouser"));
			model.setFotoExtensao(resultado.getString("fotoext"));
			model.setCep(resultado.getString("cep"));
			model.setLogradouro(resultado.getString("logradouro"));
			model.setBairro(resultado.getString("bairro"));
			model.setLocalidade(resultado.getString("localidade"));
			model.setUf(resultado.getString("uf"));
			model.setNumero(resultado.getInt("numero"));
			model.setComplemento(resultado.getString("complemento"));
			model.setDatanascimento(resultado.getDate("datanascimento"));
			model.setRendamensal(resultado.getDouble("rendamensal"));
			
			return model;			
		}
		
		
		return model;
	}
	
	public ModelLoginServlet buscarId(String id) throws SQLException {
		ModelLoginServlet model = new ModelLoginServlet();
		String sql="SELECT * FROM model_login where id = ?";		
		PreparedStatement statement= connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			model.setId(resultado.getLong("id"));
			model.setLogin(resultado.getString("login"));
			model.setSenha(resultado.getString("senha"));
			model.setNome(resultado.getString("nome"));
			model.setEmail(resultado.getString("email"));
			model.setSexo(resultado.getString("sexo"));
			model.setFotoUser(resultado.getString("fotouser"));
			model.setFotoExtensao(resultado.getString("fotoext"));
			model.setCep(resultado.getString("cep"));
			model.setLogradouro(resultado.getString("logradouro"));
			model.setBairro(resultado.getString("bairro"));
			model.setLocalidade(resultado.getString("localidade"));
			model.setUf(resultado.getString("uf"));
			model.setNumero(resultado.getInt("numero"));
			model.setComplemento(resultado.getString("complemento"));
			model.setDatanascimento(resultado.getDate("datanascimento"));
			model.setRendamensal(resultado.getDouble("rendamensal"));
			
			return model;			
		}
		
		
		return model;
	}
	
	
	
	public boolean isNew(String login) throws SQLException {
		
		String sql ="select count(1) >0 as existe from model_login where upper(login) =upper(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1,login);
		ResultSet resultado = statement.executeQuery();
		
		if(resultado.next()) {
			return resultado.getBoolean("existe");
		}
			
		return false;
	}
	
	public void deletar(String idUser) throws SQLException {
		String sql = "DELETE FROM public.model_login WHERE id = ? and usuarioadmin = false";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(idUser));
		
		statement.executeUpdate();
		connection.commit();
		
	}
	
	public BeanGrafico buscarUsuariosAdmin() throws SQLException {
		BeanGrafico dados = new BeanGrafico();
		String sql = "select count(1) usuarioadmin from model_login group by usuarioadmin";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado= statement.executeQuery();
		String[] rotulos = {"Comuns","Administradores"};
		List<Integer> inf = new ArrayList<Integer>();
		
		while(resultado.next()) {
			inf.add(resultado.getInt("usuarioadmin"));
		}
		
		dados.setRotulos(Arrays.asList(rotulos));
		dados.setValores(inf);
		
		return dados;
	}

	public int buscarNomePg(String nome, long id) throws SQLException{
		
		
		//todos os metodos que retorna uma lista com usuarios terão que ter um limite pre estabelecido pra ganhar perfomace na hora de carregar em tela.
		
		List<ModelLoginServlet> nomes = new ArrayList<ModelLoginServlet>();
		
		String sql = "select count(1) as total from model_login where upper(nome) like upper(?) and usuarioadmin= false and usuario_id= "+id+"limit 5";
		
		PreparedStatement buscarNomes= connection.prepareStatement(sql);
		buscarNomes.setString(1, "%"+nome+"%");
		ResultSet resultSet=buscarNomes.executeQuery();
		
		resultSet.next();
		Double totalRegistro= resultSet.getDouble("Total");
		Double nPagina=5.0;
		Double paginas = totalRegistro/nPagina;
		if(paginas >0) {
			paginas++;
		}
		
		return paginas.intValue();
		
	}
	
	public List<ModelLoginServlet> buscarNomePaginado(String nome, long id,String pg) throws SQLException{
		
		
		//todos os metodos que retorna uma lista com usuarios terão que ter um limite pre estabelecido pra ganhar perfomace na hora de carregar em tela.
		
		List<ModelLoginServlet> nomes = new ArrayList<ModelLoginServlet>();
		
		String sql = "select * from model_login where upper(nome) like upper(?) and usuarioadmin= false and usuario_id= "+id+"offset " +pg+ "limit 5";
		
		PreparedStatement buscarNomes= connection.prepareStatement(sql);
		buscarNomes.setString(1, "%"+nome+"%");
		ResultSet resultSet=buscarNomes.executeQuery();
		
		while(resultSet.next()) {
			ModelLoginServlet model = new ModelLoginServlet();
			model.setNome(resultSet.getString("nome"));
			model.setEmail(resultSet.getString("email"));
			model.setId(resultSet.getLong("id"));
			model.setLogin(resultSet.getString("login"));
			model.setSenha(null);
			model.setSexo(resultSet.getString("sexo"));
			model.setCep(resultSet.getString("cep"));
			model.setLogradouro(resultSet.getString("logradouro"));
			model.setBairro(resultSet.getString("bairro"));
			model.setLocalidade(resultSet.getString("localidade"));
			model.setUf(resultSet.getString("uf"));
			model.setNumero(resultSet.getInt("numero"));
			model.setComplemento(resultSet.getString("complemento"));
			model.setDatanascimento(resultSet.getDate("datanascimento"));
			model.setRendamensal(resultSet.getDouble("rendamensal"));
			
			
			nomes.add(model);
		}
		
		
		
		return nomes;
	}
	
	
	public List<ModelLoginServlet> buscarNome(String nome, long id) throws SQLException{
		
		
		//todos os metodos que retorna uma lista com usuarios terão que ter um limite pre estabelecido pra ganhar perfomace na hora de carregar em tela.
		
		List<ModelLoginServlet> nomes = new ArrayList<ModelLoginServlet>();
		
		String sql = "select * from model_login where upper(nome) like upper(?) and usuarioadmin= false and usuario_id= "+id+"limit 5";
		
		PreparedStatement buscarNomes= connection.prepareStatement(sql);
		buscarNomes.setString(1, "%"+nome+"%");
		ResultSet resultSet=buscarNomes.executeQuery();
		
		while(resultSet.next()) {
			ModelLoginServlet model = new ModelLoginServlet();
			model.setNome(resultSet.getString("nome"));
			model.setEmail(resultSet.getString("email"));
			model.setId(resultSet.getLong("id"));
			model.setLogin(resultSet.getString("login"));
			model.setSenha(null);
			model.setSexo(resultSet.getString("sexo"));
			model.setCep(resultSet.getString("cep"));
			model.setLogradouro(resultSet.getString("logradouro"));
			model.setBairro(resultSet.getString("bairro"));
			model.setLocalidade(resultSet.getString("localidade"));
			model.setUf(resultSet.getString("uf"));
			model.setNumero(resultSet.getInt("numero"));
			model.setComplemento(resultSet.getString("complemento"));
			model.setDatanascimento(resultSet.getDate("datanascimento"));
			model.setRendamensal(resultSet.getDouble("rendamensal"));
			
			
			nomes.add(model);
		}
		
		
		
		return nomes;
	}
	
	public int totalPagina(Long userLogado) throws SQLException {
		String sql = "select count(1) as total from model_login where usuario_id ="+userLogado;
		PreparedStatement statementUser = connection.prepareStatement(sql);
		ResultSet resultado= statementUser.executeQuery();
		resultado.next();
		Double totalRegistro= resultado.getDouble("Total");
		Double nPagina=5.0;
		Double paginas = totalRegistro/nPagina;
		if(paginas >0) {
			paginas++;
		}
		
		return paginas.intValue();
	}
	
	public List<ModelLoginServlet> buscarUsuariosPaginados(Long id, int offset ) throws SQLException {
		String sql = "select * from model_login where usuarioadmin = false and usuario_id= "+id +"order by nome offset "+offset+" limit 5";
		
		PreparedStatement statementUser = connection.prepareStatement(sql);
		ResultSet resultado= statementUser.executeQuery();
		List<ModelLoginServlet> usuarios = new ArrayList<ModelLoginServlet>();
		
		while(resultado.next()) {
			ModelLoginServlet modelo = new ModelLoginServlet();
			modelo.setId(resultado.getLong("id"));
			modelo.setNome(resultado.getString("nome"));
			modelo.setLogin(resultado.getString("login"));
			modelo.setSenha(resultado.getString("senha"));
			modelo.setEmail(resultado.getString("email"));
			modelo.setSexo(resultado.getString("sexo"));
			modelo.setCep(resultado.getString("cep"));
			modelo.setLogradouro(resultado.getString("logradouro"));
			modelo.setBairro(resultado.getString("bairro"));
			modelo.setLocalidade(resultado.getString("localidade"));
			modelo.setUf(resultado.getString("uf"));
			modelo.setNumero(resultado.getInt("numero"));
			modelo.setComplemento(resultado.getString("complemento"));
			modelo.setDatanascimento(resultado.getDate("datanascimento"));
			modelo.setRendamensal(resultado.getDouble("rendamensal"));
			usuarios.add(modelo);		
		}
		
		return usuarios;	
		
	}
	
	public List<ModelLoginServlet> filtrarData(Long id, Date dataInicial, Date dataFinal) throws SQLException {
		String sql = "select * from model_login where usuario_id= "+id+" and datanascimento >= '"+dataInicial+ "' and datanascimento <= '"+dataFinal+"'";
		
		PreparedStatement statementUser = connection.prepareStatement(sql);
		ResultSet resultado= statementUser.executeQuery();
		List<ModelLoginServlet> usuarios = new ArrayList<ModelLoginServlet>();
		
		while(resultado.next()) {
			ModelLoginServlet modelo = new ModelLoginServlet();
			modelo.setId(resultado.getLong("id"));
			modelo.setNome(resultado.getString("nome"));
			modelo.setLogin(resultado.getString("login"));
			modelo.setSenha(resultado.getString("senha"));
			modelo.setEmail(resultado.getString("email"));
			modelo.setSexo(resultado.getString("sexo"));
			modelo.setCep(resultado.getString("cep"));
			modelo.setLogradouro(resultado.getString("logradouro"));
			modelo.setBairro(resultado.getString("bairro"));
			modelo.setLocalidade(resultado.getString("localidade"));
			modelo.setUf(resultado.getString("uf"));
			modelo.setNumero(resultado.getInt("numero"));
			modelo.setComplemento(resultado.getString("complemento"));
			modelo.setDatanascimento(resultado.getDate("datanascimento"));
			modelo.setRendamensal(resultado.getDouble("rendamensal"));
			usuarios.add(modelo);		
		}
		
		return usuarios;	
		
	} 

	
	public List<ModelLoginServlet> buscarUsuarios(Long id ) throws SQLException {
		String sql = "select * from model_login where usuarioadmin = false and usuario_id= "+id+"limit 5";
		
		PreparedStatement statementUser = connection.prepareStatement(sql);
		ResultSet resultado= statementUser.executeQuery();
		List<ModelLoginServlet> usuarios = new ArrayList<ModelLoginServlet>();
		
		while(resultado.next()) {
			ModelLoginServlet modelo = new ModelLoginServlet();
			modelo.setId(resultado.getLong("id"));
			modelo.setNome(resultado.getString("nome"));
			modelo.setLogin(resultado.getString("login"));
			modelo.setSenha(resultado.getString("senha"));
			modelo.setEmail(resultado.getString("email"));
			modelo.setSexo(resultado.getString("sexo"));
			modelo.setCep(resultado.getString("cep"));
			modelo.setLogradouro(resultado.getString("logradouro"));
			modelo.setBairro(resultado.getString("bairro"));
			modelo.setLocalidade(resultado.getString("localidade"));
			modelo.setUf(resultado.getString("uf"));
			modelo.setNumero(resultado.getInt("numero"));
			modelo.setComplemento(resultado.getString("complemento"));
			modelo.setDatanascimento(resultado.getDate("datanascimento"));
			modelo.setRendamensal(resultado.getDouble("rendamensal"));
			usuarios.add(modelo);		
		}
		
		return usuarios;	
		
	}
	
	public List<ModelLoginServlet> buscarTodosUsuarios(Long id ) throws SQLException {
		String sql = "select * from model_login where usuarioadmin = false and usuario_id= "+id;
		
		PreparedStatement statementUser = connection.prepareStatement(sql);
		ResultSet resultado= statementUser.executeQuery();
		List<ModelLoginServlet> usuarios = new ArrayList<ModelLoginServlet>();
		
		while(resultado.next()) {
			ModelLoginServlet modelo = new ModelLoginServlet();
			modelo.setId(resultado.getLong("id"));
			modelo.setNome(resultado.getString("nome"));
			modelo.setLogin(resultado.getString("login"));
			modelo.setSenha(resultado.getString("senha"));
			modelo.setEmail(resultado.getString("email"));
			modelo.setSexo(resultado.getString("sexo"));
			modelo.setCep(resultado.getString("cep"));
			modelo.setLogradouro(resultado.getString("logradouro"));
			modelo.setBairro(resultado.getString("bairro"));
			modelo.setLocalidade(resultado.getString("localidade"));
			modelo.setUf(resultado.getString("uf"));
			modelo.setNumero(resultado.getInt("numero"));
			modelo.setComplemento(resultado.getString("complemento"));
			modelo.setDatanascimento(resultado.getDate("datanascimento"));
			modelo.setRendamensal(resultado.getDouble("rendamensal"));
			usuarios.add(modelo);		
		}
		
		return usuarios;	
		
	}
}
