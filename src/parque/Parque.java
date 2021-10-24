package parque;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.AtraccionDAOImpl;
import dao.PromocionDAOImpl;
import dao.UserDAOImpl;
import lectorDeArchivos.EscrituraDeArchivo;

public class Parque {

	public void ejecutar() {
		
		UserDAOImpl userDAO = new UserDAOImpl();
		List<Usuario> usuarios = userDAO.findAll();
		
		AtraccionDAOImpl atraccionDAO = new AtraccionDAOImpl();
		List<Atraccion> atracciones = atraccionDAO.findAll();
		
		PromocionDAOImpl promocionDAO = new PromocionDAOImpl();
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
			}

		}

	}

}
