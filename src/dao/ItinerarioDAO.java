package dao;

import java.util.List;

import parque.Producto;
import parque.Usuario;

public interface ItinerarioDAO{

	public abstract List<Producto> findAllOfUser(int id);
	public abstract int countAllOfUser(int id);
	public abstract int insert(Producto t, Usuario u);
	public abstract int update(Producto t, Usuario u);
	public abstract int delete(Usuario usuario);
}
