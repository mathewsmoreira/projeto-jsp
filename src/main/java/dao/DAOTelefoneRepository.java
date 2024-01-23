package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import connection.SingleConnectionBanco;
import model.ModelTelefone;

public class DAOTelefoneRepository {
	private DAOLoginRepository dao = new DAOLoginRepository();

	private Connection connection;

	public DAOTelefoneRepository() {

		connection = SingleConnectionBanco.getConnection();

	}

	public void gravarTelefone(ModelTelefone modelTelefone) throws SQLException {

		String sql = "insert into telefone (numero, usuario_pai_id, usuario_cad_id) values(?,?,?)";

		try {
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, modelTelefone.getNumero());
		statement.setLong(2, modelTelefone.getUsuario_pai_id().getId());
		statement.setLong(3, modelTelefone.getUsuario_cad_id().getId());

		statement.execute();
		connection.commit();
		}catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}
	}

	public void updateTelefone(ModelTelefone modelTelefone, String telefone) throws SQLException {

		String sql = "update telefone set numero =?, usuario_pai_id = ?, usuario_cad_id = ? where numero = ?";

		try {
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, modelTelefone.getNumero());
		statement.setLong(2, modelTelefone.getUsuario_pai_id().getId());
		statement.setLong(3, modelTelefone.getUsuario_cad_id().getId());
		statement.setString(4, telefone);

		statement.executeUpdate();
		connection.commit();
		}catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}

	}

	public void deletarTelefone(String numero) throws SQLException {
		String sql = "delete from telefone where numero =?";
		try {
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, numero);
		statement.executeUpdate();
		connection.commit();
		}catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}

	}

	public ModelTelefone FoneId(String id) throws SQLException {

		ModelTelefone tl = new ModelTelefone();
		String sql = "select * from telefone where usuario_pai_id = ?";
		try {
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));

		ResultSet rs = statement.executeQuery();
		DAOLoginRepository dao = new DAOLoginRepository();

		while (rs.next()) {
			tl.setId(rs.getLong("usuario_pai_id"));
			tl.setNumero(rs.getString("numero"));

		}

		return tl;
		}catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}
		return null;
	}

	public ModelTelefone buscarId(String id) throws SQLException {

		ModelTelefone tl = new ModelTelefone();
		String sql = "select * from telefone where id = ?";

		try {
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, Integer.parseInt(id));

		ResultSet rs = statement.executeQuery();
		DAOLoginRepository dao = new DAOLoginRepository();

		while (rs.next()) {
			tl.setId(rs.getLong("usuario_pai_id"));
			tl.setNumero(rs.getString("numero"));

		}

		return tl;
		}catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}
		return null;
	}

	public boolean isNew(String numero) throws SQLException {
		String sql = "select count(2) as existe from telefone  where numero = ?";
		try {
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, numero);
		ResultSet rs = statement.executeQuery();
		int existe = 0;
		while (rs.next()) {
			existe = rs.getInt("existe");
		}

		boolean isNew = (existe >= 1 ? false : true);

		return isNew;
		}catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}
		return false;
	}

	public List<ModelTelefone> listFones(Long id) throws SQLException {

		List<ModelTelefone> telefones = new ArrayList<ModelTelefone>();
		String sql = "select * from telefone where usuario_pai_id = ?";
		try {
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, id);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			ModelTelefone m = new ModelTelefone();
			m.setId(rs.getLong("id"));
			m.setNumero(rs.getString("numero"));
			m.setUsuario_cad_id(dao.pesquisarId(String.valueOf(rs.getLong("usuario_cad_id"))));
			m.setUsuario_pai_id(dao.pesquisarId(String.valueOf(rs.getLong("usuario_pai_id"))));
			telefones.add(m);

		}

		return telefones;
		}catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}
		return null;
	}


}
