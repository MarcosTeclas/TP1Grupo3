package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionProvider;
import parque.Atraccion;
import parque.Producto;
import parque.TipoDeAtraccion;
import parque.Usuario;

public class UserDAOImpl implements UserDAO {

	public int insert(Usuario usuario) {
		try {
			String sql = "INSERT INTO USUARIOS (NOMBRE, ATRACCION_PREFERIDA, DINERO, TIEMPO) VALUES (?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, usuario.getNombre());
			statement.setString(2, usuario.getAtraccionPreferida().toString());
			statement.setDouble(3, usuario.getDinero());
			statement.setDouble(4, usuario.getTiempo());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int update(Usuario usuario) {
		try {
			String sql = "UPDATE USUARIOS SET DINERO_DISPONIBLE = ?, TIEMPO_DISPONIBLE = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setDouble(1, usuario.getDinero());
			statement.setDouble(2, usuario.getTiempo());
			statement.setInt(3, usuario.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(Usuario usuario) {
		try {
			String sql = "DELETE FROM USUARIOS WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, usuario.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Usuario findById(int id) {
		try {
			String sql = "SELECT * FROM USUARIOS WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Usuario usuario = null;

			if (resultados.next()) {
				usuario = toUser(resultados);
			}

			return usuario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM USUARIOS";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<Usuario> findAll() {
		try {
			String sql = "SELECT * FROM USUARIOS";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Usuario> usuarios = new ArrayList<Usuario>();
			while (resultados.next()) {
				usuarios.add(toUser(resultados));
			}

			return usuarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Usuario toUser(ResultSet resultados) throws SQLException {		
		List<Producto> itinerario = getProductosComprados(resultados.getInt(1));		
		Usuario usuario = new Usuario(resultados.getInt(1), resultados.getString(2), 
				TipoDeAtraccion.valueOf(resultados.getString(3)), resultados.getDouble(4), resultados.getDouble(5), itinerario);
		return usuario;
	}
	
	private List<Producto> getProductosComprados(int idUsuario) {
		try {
			String sql = "SELECT PROMOCIONES.ID FROM PROMOCIONES JOIN ITINERARIO_USUARIO ON ITINERARIO_USUARIO.PROMOCION_COMPRADA_ID "
					+ "= PROMOCIONES.ID WHERE ITINERARIO_USUARIO.USUARIO_ID = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idUsuario);
			ResultSet resultados = statement.executeQuery();

			List<Producto> atraccionesIncluidas = new ArrayList<Producto>();
			PromocionDAOImpl promocionDao = new PromocionDAOImpl();

			while (resultados.next()) {
				atraccionesIncluidas.add(promocionDao.findById(resultados.getInt(1)));
			}
			conn.close();
			
			String sql2 = "SELECT ATRACCIONES.ID FROM ATRACCIONES JOIN ITINERARIO_USUARIO ON "
					+ "ITINERARIO_USUARIO.ATRACCION_COMPRADA_ID = ATRACCIONES.ID WHERE ITINERARIO_USUARIO.USUARIO_ID= ?";
			Connection conn2 = ConnectionProvider.getConnection();
			PreparedStatement statement2 = conn2.prepareStatement(sql2);
			statement2.setInt(1, idUsuario);
			ResultSet resultados2 = statement2.executeQuery();

			AtraccionDAOImpl atraccionDAO = new AtraccionDAOImpl();

			while (resultados2.next()) {
				Atraccion atraccion = atraccionDAO.findById(resultados2.getInt(1));
				atraccionesIncluidas.add(atraccion);
			}
			return atraccionesIncluidas;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	}