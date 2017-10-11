package br.com.livro.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO extends BaseDAO {

	public Carro createCarro(ResultSet rs) {

		Carro c = new Carro();
		try {
			c.setId(rs.getLong("id"));
			c.setNome(rs.getString("nome"));
			c.setDesc(rs.getString("descricao"));
			c.setLongitude(rs.getString("longitude"));
			c.setLatitude(rs.getString("latitude"));
			c.setUrlFoto(rs.getString("url_foto"));
			c.setUrlVideo(rs.getString("url_video"));
			c.setTipo(rs.getString("tipo"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	public Carro getCarroById(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from carro where id = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Carro c = createCarro(rs);
				rs.close();
				return c;
			}

		} finally {
			if (conn != null) {
				conn.close();
			}

			if (stmt != null) {
				stmt.close();
			}
		}

		return null;
	}

	public List<Carro> getCarroByName(String name) throws SQLException {
		List<Carro> carros = new ArrayList<Carro>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from carro where lower(nome) like ?");
			stmt.setString(1, "%" + name.toLowerCase() + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Carro c = createCarro(rs);
				carros.add(c);
			}
			rs.close();

		} finally {
			if (conn != null) {
				conn.close();
			}

			if (stmt != null) {
				stmt.close();
			}
		}

		return carros;
	}

	public List<Carro> getCarroByTipo(String tipo) throws SQLException {
		List<Carro> carros = new ArrayList<Carro>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from carro where tipo = ?");
			stmt.setString(1, tipo);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Carro c = createCarro(rs);
				carros.add(c);
			}
			rs.close();

		} finally {
			if (conn != null) {
				conn.close();
			}

			if (stmt != null) {
				stmt.close();
			}
		}

		return carros;
	}

	public List<Carro> getCarros() throws SQLException {
		List<Carro> carros = new ArrayList<Carro>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from carro");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Carro c = createCarro(rs);
				carros.add(c);
			}
			rs.close();

		} finally {
			if (conn != null) {
				conn.close();
			}

			if (stmt != null) {
				stmt.close();
			}
		}

		return carros;
	}

	public void save(Carro c) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();

			if (c.getId() == null) {
				stmt = conn.prepareStatement(
						"INSERT INTO carro (nome, descricao, url_foto, url_video, latitude, longitude, tipo) values(?,?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn.prepareStatement(
						"UPDATE carro SET nome=?, descricao=?, url_foto=?, url_video=?, latitude=?, longitude=?, tipo=? where id=?");
			}
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getDesc());
			stmt.setString(3, c.getUrlFoto());
			stmt.setString(4, c.getUrlVideo());
			stmt.setString(5, c.getLatitude());
			stmt.setString(6, c.getLongitude());
			stmt.setString(7, c.getTipo());
			if (c.getId() != null) {
				stmt.setLong(8, c.getId());
			}

			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir o carro");
			}

			if (c.getId() == null) {
				Long id = getGeneratorId(stmt);
				c.setId(id);
			}

		} finally {
			if (conn != null) {
				conn.close();
			}

			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public static Long getGeneratorId(Statement stmt) throws SQLException {
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			Long id = rs.getLong(1);
			return id;
		}
		return 0L;
	}

	public boolean delete(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("delete from carro where id = ?");
			stmt.setLong(1, id);
			int count = stmt.executeUpdate();
			boolean ok = count > 0;
			return ok;
		} finally {
			if (conn != null) {
				conn.close();
			}

			if (stmt != null) {
				stmt.close();
			}
		}
	}
}
