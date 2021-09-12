package parque;

import java.util.LinkedList;
import java.util.List;

import lectorDeArchivos.LectorDeAtracciones;
import lectorDeArchivos.LectorDePromociones;
import lectorDeArchivos.LectorDeUsuarios;

public class Parque {

	private List<Usuario> usuarios = new LinkedList<Usuario>();
	private List<Producto> productos = new LinkedList<Producto>();
	
	public void ejecutar() 
	{
		List<Promocion> promociones = new LinkedList<Promocion>();
		List<Atraccion> atracciones = new LinkedList<Atraccion>();
		LectorDeUsuarios usu=new LectorDeUsuarios();
		usuarios=usu.leerUsuario("archivos/usuario.csv");
		LectorDeAtracciones atraccion = new LectorDeAtracciones();
		atracciones = atraccion.leerAtraccion("archivos/atracciones.csv");
		LectorDePromociones promos = new LectorDePromociones();
		promociones= promos.leerPromocion("archivos/promociones.csv", atracciones);
		productos.addAll(atracciones);
		productos.addAll(promociones);
		
		for (Usuario usuario: usuarios) 
		{
			productos.sort(new ComparablePorAtraccionPreferida(usuario.getAtraccionPreferida()));
			
			for(Producto producto: productos) 
			{
				
			}
		}
		
	}

}
