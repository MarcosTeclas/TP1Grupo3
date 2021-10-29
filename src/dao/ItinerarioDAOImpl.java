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
import parque.Promocion;
import parque.Usuario;

public class ItinerarioDAOImpl implements ItinerarioDAO {

	public int insert(Producto producto, Usuario usuario) {
		try {
			String sql = "INSERT INTO ITINERARIO_USUARIO (USUARIO_ID, ATRACCION_COMPRADA_ID, PROMOCION_COMPRADA_ID) VALUES (?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, usuario.getId());

			if (producto.esPromo()) {
				statement.setInt(3, producto.getId());
			} else {
				statement.setInt(2, producto.getId());
			}
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int update(Producto producto, Usuario usuario) {
		try {
			String sql = "UPDATE ITINERARIO_USUARIO SET ATRACCION_COMPRADA_ID = ?, PROMOCION_COMPRADA_ID = ? WHERE USUARIO_ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(2, usuario.getId());
			if (producto.esPromo()) {
				statement.setInt(2, producto.getId());
			} else {
				statement.setInt(3, producto.getId());
			}
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(Usuario usuario) {
		try {
			String sql = "DELETE FROM ITINERARIO_USUARIO WHERE USUARIO_ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, usuario.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	/*public Producto findById(int id) {
		try {
			String sql = "SELECT * FROM ITINERARIO_USUARIO WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccion = null;

			if (resultados.next()) {
				atraccion = toAtraccion(resultados);
			}

			return atraccion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}*/

	public int countAllOfUser(int id) {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM ITINERARIO_USUARIO WHERE USUARIO_ID = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<Producto> findAllOfUser(int id) {
		try {
			String sql = "SELECT * FROM ITINERARIO_USUARIO WHERE USUARIO_ID = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			List<Producto> productos = new ArrayList<Producto>();
			while (resultados.next()) {
				if (resultados.getInt(2) == 0) { //si no es atracción
					productos.add(toPromocion(resultados));
				} else if (resultados.getInt(3) == 0) { //si no es promoción
					productos.add(toAtraccion(resultados));
				} 
			}

			return productos;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Atraccion toAtraccion(ResultSet resultados) throws SQLException {
		AtraccionDAOImpl atraccion = new AtraccionDAOImpl();
		return atraccion.findById(resultados.getInt(2));
	}

	private Promocion toPromocion(ResultSet resultados) throws SQLException {
		PromocionDAOImpl promocion = new PromocionDAOImpl();
		return promocion.findById(resultados.getInt(2));
	}
}