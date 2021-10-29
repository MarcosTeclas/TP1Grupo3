package parque;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.AtraccionDAO;
import dao.AtraccionDAOImpl;
import dao.DAOFactory;
import dao.ItinerarioDAOImpl;
import dao.PromocionDAO;
import dao.PromocionDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;

public class Parque {

	public void ejecutar() {

		UserDAO userDAO = DAOFactory.getUserDAO();
		List<Usuario> usuarios = userDAO.findAll();

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		List<Atraccion> atracciones = atraccionDAO.findAll();

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		List<Promocion> promociones = promocionDAO.findAll();

		List<Producto> productos = new ArrayList<Producto>();
		productos.addAll(atracciones);
		productos.addAll(promociones);

		try (Scanner entrada = new Scanner(System.in)) {
			String confirmacion;
			ItinerarioDAOImpl itinerario = new ItinerarioDAOImpl();

			for (Usuario usuario : usuarios) {
				System.out.println("Bienvenido " + usuario.getNombre());
				productos.sort(new ComparablePorAtraccionPreferida(usuario.getAtraccionPreferida()));
				for (Producto producto : productos) {
					if (usuario.puedeComprarProducto(producto)) {
						System.out.println(producto);
						System.out.println("SI = S o NO = N ");
						confirmacion = entrada.nextLine().toUpperCase();
						while (!confirmacion.equals("S") && !confirmacion.equals("N")) {
							System.out.println("SI = S o NO = N ");
							confirmacion = entrada.nextLine();
						}

						if (confirmacion.equals("S")) {
							usuario.comprarProducto(producto);
							itinerario.insert(producto, usuario);
							actualizarProducto(producto);
						}
					}
					actualizarUsuario(usuario);
				}
				System.out.println(usuario.getItinerario());
				System.out.println();
				System.out.println();
				System.out.println("¡GRACIAS POR PASAR EL DÍA EN NUESTRO PARQUE!");
				System.out.println();
				System.out.println();
				System.out.println("------------------------------------------------");
			}
		}
	}
	
	public void actualizarProducto(Producto producto) {
		if(producto.esPromo()) {
			PromocionDAOImpl promocionDao = new PromocionDAOImpl();
			promocionDao.update((Promocion) producto);
		}else {
			AtraccionDAOImpl atraccionDao = new AtraccionDAOImpl();
			atraccionDao.update((Atraccion) producto);
		}		
	}
	
	public void actualizarUsuario (Usuario usuario) {
		UserDAOImpl user = new UserDAOImpl();
		user.update(usuario);
	}
	

}
