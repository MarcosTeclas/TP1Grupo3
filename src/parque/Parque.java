package parque;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lectorDeArchivos.EscrituraDeArchivo;
import lectorDeArchivos.LectorDeAtracciones;
import lectorDeArchivos.LectorDePromociones;
import lectorDeArchivos.LectorDeUsuarios;

public class Parque {

	public void ejecutar() {
		List<Producto> productos = new ArrayList<Producto>();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		List<Promocion> promociones = new ArrayList<Promocion>();
		List<Atraccion> atracciones = new ArrayList<Atraccion>();

		LectorDeUsuarios usu = new LectorDeUsuarios();
		usuarios = usu.leerUsuario("archivos/usuario.csv");

		LectorDeAtracciones atraccion = new LectorDeAtracciones();
		atracciones = atraccion.leerAtraccion("archivos/atracciones.csv");

		LectorDePromociones promos = new LectorDePromociones();
		promociones = promos.leerPromocion("archivos/promociones.csv", atracciones);

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
