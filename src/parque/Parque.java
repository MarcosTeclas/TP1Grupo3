package parque;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.AtraccionDAO;
import dao.DAOFactory;
import dao.PromocionDAO;
import dao.UserDAO;
import lectorDeArchivos.EscrituraDeArchivo;

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
			EscrituraDeArchivo escritura = new EscrituraDeArchivo();

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
						}
					}
				}
				System.out.println(usuario.getItinerario());
				escritura.escribirItinerario(usuario, usuario.getItinerario());
				System.out.println();
				System.out.println();
				System.out.println("¡GRACIAS POR PASAR EL DÍA EN NUESTRO PARQUE!");
				System.out.println();
				System.out.println();
				System.out.println("------------------------------------------------");
			}

		}

	}

}
