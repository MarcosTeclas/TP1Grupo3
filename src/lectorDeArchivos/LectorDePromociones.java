package lectorDeArchivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import parque.Atraccion;
import parque.PromoAbsoluta;
import parque.PromoAxB;
import parque.PromoPorcentual;
import parque.Promocion;
import parque.TipoDeAtraccion;

public class LectorDePromociones {

	// lee un archivo csv y me devuelve una lista de atracciones
	public List<Promocion> leerPromocion(String archivo, List<Atraccion> atraccionesEnElParque) {

		List<Promocion> promos = new ArrayList<Promocion>();
		FileReader fr = null;
		BufferedReader br = null;
		String linea = null;

		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			while ((linea = br.readLine()) != null) {

				try {
					promos.add(crearPromo(linea, atraccionesEnElParque));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

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

	private Promocion crearPromo(String linea, List<Atraccion> atraccionesEnElParque) throws PromocionException {
		Promocion promo = null;
		String[] datos = linea.split(",");

		try {
			if (datos[0].equals("Porcentual")) {
				String[] atracciones = new String[datos.length - 4];
				int cont = 0;
				for (int i = 4; i < datos.length; i++) {
					atracciones[cont] = datos[i];
					cont++;
				}

				List<Atraccion> atraccionesIncluidas = getAtraccionesIncluidas(atracciones, atraccionesEnElParque);
				promo = new PromoPorcentual(datos[1], TipoDeAtraccion.valueOf(datos[2].toUpperCase()),
						Integer.parseInt(datos[3]), atraccionesIncluidas);

			} else if (datos[0].equals("Absoluta")) {
				String[] atracciones = new String[datos.length - 4];
				int cont = 0;
				for (int i = 4; i < datos.length; i++) {
					atracciones[cont] = datos[i];
					cont++;
				}

				List<Atraccion> atraccionesIncluidas = getAtraccionesIncluidas(atracciones, atraccionesEnElParque);

				promo = new PromoAbsoluta(datos[1], TipoDeAtraccion.valueOf(datos[2].toUpperCase()),
						Integer.parseInt(datos[3]), atraccionesIncluidas);
			}  else if (datos[0].equals("AxB")) {
				String[] atracciones = new String[datos.length - 3];
				int cont = 0;
				for (int i = 3; i < datos.length; i++) {
					atracciones[cont] = datos[i];
					cont++;
				}

				List<Atraccion> atraccionesIncluidas = getAtraccionesIncluidas(atracciones, atraccionesEnElParque);

				promo = new PromoAxB(datos[1], TipoDeAtraccion.valueOf(datos[2].toUpperCase()), atraccionesIncluidas);
			}
		} catch (NumberFormatException e) {
			throw new PromocionException("No es un numero");
		} catch (Exception e) {
			throw new PromocionException("No es un enumerado");
		}

		return promo;

	}

	private List<Atraccion> getAtraccionesIncluidas(String[] atracciones, List<Atraccion> atraccionesEnElParque) {
		List<Atraccion> atraccionesIncluidas = new ArrayList<Atraccion>();
		for (String atraccion : atracciones) {
			for (Atraccion atraccionExistente : atraccionesEnElParque) {
				if (atraccionExistente.getNombre().equals(atraccion)) {

					atraccionesIncluidas.add(atraccionExistente);
				}
			}
		}
		return atraccionesIncluidas;
	}

}
