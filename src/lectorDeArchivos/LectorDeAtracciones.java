package lectorDeArchivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import parque.Atraccion;
import parque.TipoDeAtraccion;

public class LectorDeAtracciones {

	// lee un archivo csv y me devuelve una lista de atracciones
	public List<Atraccion> leerAtraccion(String archivo) {
		List<Atraccion> juegos = new LinkedList<Atraccion>();
		FileReader fr = null;
		BufferedReader br = null;
		String linea = null;
		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			linea = br.readLine();
			while (linea != null) {

				try {
					juegos.add(crearAtraccion(linea));
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
		return juegos;
	}

	private Atraccion crearAtraccion(String linea) throws AtraccionException {
		Atraccion atraccion;
		String[] datos = linea.split(",");
		if (datos.length != 5) {
			throw new AtraccionException("cantidad de datos incorrectos");
		}

		try
		{
			atraccion = new Atraccion(datos[0], Double.parseDouble(datos[1]),
					Double.parseDouble(datos[2]),Integer.parseInt(datos[3]),
					TipoDeAtraccion.valueOf(datos[4].toUpperCase()));

		}
		catch(NumberFormatException e) 
		{
			throw new AtraccionException("No es un numero");
		}
		catch(Exception e) 
		{
			throw new AtraccionException("No es un enumerado");
		}
		
		return atraccion;

	}

	public static void main(String[] args) {

		LectorDeAtracciones atrac = new LectorDeAtracciones();

		atrac.leerAtraccion("archivos/atracciones.csv");
	}
}
