package lectorDeArchivos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import parque.Producto;
import parque.Usuario;

public class EscrituraDeArchivo {
	BufferedWriter bw;

	public void escribirItinerario(Usuario usuario, List<Producto> itinerario) {
		try {
			bw = new BufferedWriter(new FileWriter("Itinerario" + usuario.getNombre() + ".txt"));
			bw.write("Itinerario");
			bw.newLine();
			for (Producto producto : itinerario) {

				bw.write(producto.convertirParaMostrarArchivo());							
				bw.newLine();
			}
			bw.write("Gasto Total de Oro: " + usuario.getDineroTotal() + " y gasto Total de Tiempo: "
					+ usuario.getTiempoTotal());
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
