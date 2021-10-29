package parque;

import java.util.Iterator;
import java.util.List;

public class Usuario {
	
	private int id;
	private String nombre;
	private TipoDeAtraccion preferida;
	private double dinero, tiempo;
	private List<Producto> itinerario;

	public Usuario(int id, String nombre, TipoDeAtraccion preferencia, double dinero, double tiempo, List<Producto> itinerario) {
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
		this.id = id;
		this.nombre = nombre;
		this.preferida = preferencia;
		this.itinerario = itinerario;
	}
	
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
	
	public double getDinero() {
		return dinero;
	}
	
	public double getTiempo() {
		return tiempo;
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
		this.tiempo -= producto.getTiempoNecesario();
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
