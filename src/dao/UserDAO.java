package dao;

import parque.Usuario;

public interface UserDAO extends GenericDAO<Usuario> {

	public abstract Usuario findById(int id);
	
}