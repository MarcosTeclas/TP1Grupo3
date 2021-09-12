package lectorDeArchivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import parque.Atraccion;
import parque.PromoAbsoluta;
import parque.PromoPorcentual;
import parque.Promocion;
import parque.TipoDeAtraccion;

public class LectorDePromociones {

	LectorDeAtracciones atrac = new LectorDeAtracciones();
	List<Atraccion> atraccionesEnElParque = atrac.leerAtraccion("archivos/atracciones.csv");

	// lee un archivo csv y me devuelve una lista de atracciones
	public List<Promocion> leerPromocion(String archivo) {

		List<Promocion> promos = new LinkedList<Promocion>();
		FileReader fr = null;
		BufferedReader br = null;
		String linea = null;

		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			linea = br.readLine();
			while (linea != null) {

				try {
					promos.add(crearPromo(linea));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				System.out.println(linea);
				linea = br.readLine();
			}
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return promos;
	}

	private Promocion crearPromo(String linea) throws PromocionException {
		Promocion promo = null;
		String[] datos = linea.split(",");
		if (datos.length != 5) {
			throw new PromocionException("cantidad de datos incorrectos");
		}

		try {
			if (datos[0].equals("Porcentual")) {
				String[] atracciones = new String[datos.length-4];
				int cont = 0;
				for (int i = 4; i < datos.length; i++) {
					atracciones[cont] = datos[i];
					cont++;
				}
				List<Atraccion> atraccionesIncluidas = getAtraccionesIncluidas(atracciones);

				
				promo = new PromoPorcentual(datos[1],TipoDeAtraccion.valueOf(datos[2].toUpperCase()), 
						Integer.parseInt(datos[3]), atraccionesIncluidas);
			
			}else if (datos[0].equals("Absoluta")) {
				String[] atracciones = new String[datos.length-4];
				int cont = 0;
				for (int i = 4; i < datos.length; i++) {
					atracciones[cont] = datos[i];
					cont++;
				}
				List<Atraccion> atraccionesIncluidas = getAtraccionesIncluidas(atracciones);

				
				promo = new PromoAbsoluta(datos[1],TipoDeAtraccion.valueOf(datos[2].toUpperCase()), 
						Integer.parseInt(datos[3]), atraccionesIncluidas);
				
				
				
				//falta promo AxB
			}
		} catch (NumberFormatException e) {
			throw new PromocionException("No es un numero");
		} catch (Exception e) {
			throw new PromocionException("No es un enumerado");
		}

		return promo;

	}

	private List<Atraccion> getAtraccionesIncluidas(String[] atracciones) {
		List<Atraccion> atraccionesIncluidas = new ArrayList<Atraccion>();
		for (String atraccion : atracciones) {
//			for (Atraccion atraccionExiste : atraccionesEnElParque) {
			if (atraccionesEnElParque.contains(atraccion)) {
				int indice = atraccionesEnElParque.indexOf(atraccion);
				atraccionesIncluidas.add(atraccionesEnElParque.get(indice));
			}
		}
		return atraccionesIncluidas;
	}

	public static void main(String[] args) {

		LectorDePromociones atra = new LectorDePromociones();

		atra.leerPromocion("archivos/atracciones.csv");
	}
}
