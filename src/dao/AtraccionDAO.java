package dao;

import parque.Atraccion;

public interface AtraccionDAO extends GenericDAO<Atraccion> {
	public abstract Atraccion findById(int id);

}
