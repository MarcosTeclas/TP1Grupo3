package parque;

import java.util.Iterator;
import java.util.List;

public abstract class Promocion extends Producto {

	protected List<Atraccion> atraccionesIncluidas;

	public Promocion(int id, TipoDeAtraccion tipo, String nombre, List<Atraccion> atraccionesIncluidas) {
		super(id, nombre, tipo);
		this.atraccionesIncluidas = atraccionesIncluidas;
	}

	@Override
	public boolean esPromo() {
		return true;
	}

	public String stringAtraccionesIncluidas() {
		String resultado = "";
		for (Atraccion atraccion : atraccionesIncluidas) {
			resultado += atraccion.getNombre() + " ";
		}
		return resultado;
	}

	public double getTiempoNecesario() {
		double tiempo = 0;
		for (Atraccion atrac : atraccionesIncluidas) {
			tiempo += atrac.getTiempoNecesario();
		}
		return tiempo;
	}
	
	@Override
	public int getId() {
		return id;
	}
	
	public List<Atraccion> getAtraccionesIncluidas() {
		return atraccionesIncluidas;
	}

	public void setAtraccionesIncluidas(List<Atraccion> atraccionesIncluidas) {
		this.atraccionesIncluidas = atraccionesIncluidas;
	}

	@Override
	public String toStringParaMostrar() {
		return "PROMO: " + getNombre() + ". Atracciones incluidas: " + stringAtraccionesIncluidas();
	}
	
	@Override
	public String toString() {
		return "PROMO: " + nombre + ", costo: " + costo + ", tiempo necesario: " + tiempoNecesario 
				+ ", tipo: " + tipo + ", atracciones Incluidas: " + stringAtraccionesIncluidas();
	}

	public void restarCupo() {
		for (Atraccion atrac : atraccionesIncluidas) {
			atrac.restarCupo();
		}
	}

	@Override
	public boolean hayCupo() {
		int cantAtraccionesConCupo = 0;
		for (Atraccion atrac : atraccionesIncluidas) {
			if (atrac.quedaCupo()) {
				cantAtraccionesConCupo++;
			}
		}
		return cantAtraccionesConCupo == atraccionesIncluidas.size();
	}
	
	@Override
	public boolean contiene(Producto producto) {
		boolean contiene = false;
		Iterator<Atraccion> itr = atraccionesIncluidas.iterator();
		while(!contiene && itr.hasNext()) {
			contiene = producto.contiene(itr.next());
		}
		return contiene;
	}

}
