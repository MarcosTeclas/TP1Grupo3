package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionProvider;
import parque.Atraccion;
import parque.PromoAbsoluta;
import parque.PromoAxB;
import parque.PromoPorcentual;
import parque.Promocion;
import parque.TipoDeAtraccion;

public class PromocionDAOImpl implements GenericDAO<Promocion> {

	public int insert(Promocion promocion) {
		try {
			String sql = "INSERT INTO PROMOCIONES (NOMBRE, TIPO_ATRACCION, COSTO, ATRACCION_GRATUITA, DESCUENTO) VALUES (?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, promocion.getNombre());
			statement.setString(2, promocion.getTipo().toString());
			statement.setDouble(3, promocion.getCosto());
			// statement.setString(4, promocion.getAtraccionGratuita());
			// statement.setInt(5, promocion.getDescuento());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int update(Promocion promocion) {
		try {
			String sql = "UPDATE PROMOCIONES SET NOMBRE = ?, TIPO_ATRACCION = ?, COSTO = ?, ATRACCION_GRATUITA = ?, DESCUENTO = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, promocion.getNombre());
			statement.setString(2, promocion.getTipo().toString());
			statement.setDouble(3, promocion.getCosto());
			// statement.setString(4, promocion.getAtraccionGratuita());
			// statement.setInt(5, promocion.getDescuento());
			statement.setInt(6, promocion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(Promocion promocion) {
		try {
			String sql = "DELETE FROM PROMOCIONES WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promocion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Promocion findById(int id) {
		try {
			String sql = "SELECT * FROM PROMOCIONES WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Promocion promocion = null;

			if (resultados.next()) {
				if (resultados.getInt(1) == 1) {
					promocion = toPromoPorcentual(resultados);
				} else if (resultados.getInt(1) == 2) {
					promocion = toPromoAbsoluta(resultados);
				} else if (resultados.getInt(1) == 3) {
					promocion = toPromoAxB(resultados);
				}
			}

			return promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM PROMOCIONES";
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

	public List<Promocion> findAll() {
		try {
			String sql = "SELECT * FROM PROMOCIONES";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Promocion> promociones = new ArrayList<Promocion>();
			while (resultados.next()) {
				if (resultados.getInt(1) == 1) {
					promociones.add(toPromoPorcentual(resultados));
				} else if (resultados.getInt(1) == 2) {
					promociones.add(toPromoAbsoluta(resultados));
				} else if (resultados.getInt(1) == 3) {
					promociones.add(toPromoAxB(resultados));
				}
			}

			return promociones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private List<Atraccion> atraccionesIncluidas(int idPromocion) {
		try {
			String sql = "SELECT ATRACCIONES.ID FROM PROMOCIONES JOIN ATRACCIONES_PROMOCIONES ON ATRACCIONES_PROMOCIONES.PROMOCION_ID"
					+ " = PROMOCIONES.ID JOIN ATRACCIONES ON ATRACCIONES.ID = ATRACCIONES_PROMOCIONES.ATRACCION_ID WHERE "
					+ "PROMOCIONES.ID = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();
			statement.setInt(1, idPromocion);

			List<Atraccion> atraccionesIncluidas = new ArrayList<Atraccion>();
			AtraccionDAOImpl atraccionDAO = new AtraccionDAOImpl();

			while (resultados.next()) {
				Atraccion atraccion = atraccionDAO.findById(idPromocion);
				atraccionesIncluidas.add(atraccion);
			}

			return atraccionesIncluidas;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Promocion toPromoPorcentual(ResultSet resultados) throws SQLException {
		Promocion promocionPorcentual = new PromoPorcentual(resultados.getString(2),
				TipoDeAtraccion.valueOf(resultados.getString(3)), resultados.getInt(6), this.atraccionesIncluidas(1));
		return promocionPorcentual;
	}

	private Promocion toPromoAbsoluta(ResultSet resultados) throws SQLException {
		Promocion promocionAbsoluta = new PromoAbsoluta(resultados.getString(2),
				TipoDeAtraccion.valueOf(resultados.getString(3)), resultados.getDouble(4),
				this.atraccionesIncluidas(2));
		return promocionAbsoluta;
	}

	private Promocion toPromoAxB(ResultSet resultados) throws SQLException {
		Promocion promocionAxB = new PromoAxB(resultados.getString(2), TipoDeAtraccion.valueOf(resultados.getString(3)),
				this.atraccionesIncluidas(3));
		return promocionAxB;
	}

}