package parque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Usuario {
	
	private int id;
	private String nombre;
	private TipoDeAtraccion preferida;
	private double dinero, tiempo, dineroTotal, tiempoTotal;
	private List<Producto> itinerario;

	public Usuario(String nombre, TipoDeAtraccion preferencia, double dinero, double tiempo) {
		if (validaNumeros(dinero)) {
			this.dinero = dinero;
		} else {
			this.dinero = 0;
		}
		if (validaNumeros(tiempo)) {
			this.tiempo = tiempo;
		} else {
			this.tiempo = 0;
		}

		this.nombre = nombre;
		this.preferida = preferencia;
		itinerario = new ArrayList<Producto>();
	}
	
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public double getDineroTotal() {
		return dineroTotal;
	}

	public double getTiempoTotal() {
		return tiempoTotal;
	}

	public TipoDeAtraccion getAtraccionPreferida() {
		return preferida;
	}

	public List<Producto> getItinerario() {
		return itinerario;
	}

	// Validacion de Doubles positivos
	private boolean validaNumeros(double valor) {
		boolean confirmacion = false;
		if (valor < 0) {
			throw new NumberFormatException("El valor no puede ser negativo");
		} else {
			confirmacion = true;
		}
		return confirmacion;
	}

	public boolean puedeComprarProducto(Producto producto) {
		return this.dinero >= producto.getCosto() && this.tiempo >= producto.getTiempoNecesario()
				&& !estaEnElItinerario(producto) && producto.hayCupo();
	}

	public void comprarProducto(Producto producto) {
		this.dinero -= producto.getCosto();
		this.dineroTotal += producto.getCosto();
		this.tiempo -= producto.getTiempoNecesario();
		this.tiempoTotal += producto.getTiempoNecesario();
		this.itinerario.add(producto);
		producto.restarCupo();
	}

	private boolean estaEnElItinerario(Producto producto) {
		boolean existe = false;
		Iterator<Producto> itr = this.itinerario.iterator();
		while(!existe && itr.hasNext()) {
			existe = producto.contiene(itr.next());
		}
		return existe;
	}
	


	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", atracción preferida=" + preferida + ", dinero disponible=" + dinero
				+ ", tiempo disponible=" + tiempo + "]";
	}
}
