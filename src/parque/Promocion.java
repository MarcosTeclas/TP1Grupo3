package parque;

import java.util.ArrayList;
import java.util.List;

public abstract class Promocion extends Producto {

	protected List<Atraccion> atraccionesIncluidas;

	public Promocion() {
		super();
	}

	//constructor promo porcentual
	public Promocion(TipoDeAtraccion tipo, String nombre, List<Atraccion> atraccionesIncluidas) {
		super(nombre, tipo);
		this.atraccionesIncluidas = atraccionesIncluidas;
	}
	
	//constructor para promo absoluta
	public Promocion(String nombre, TipoDeAtraccion tipo, int costo, List<Atraccion> atraccionesIncluidas) {
		super(nombre, tipo, costo);
		this.atraccionesIncluidas = atraccionesIncluidas;
	}
	


	@Override
	public boolean esPromo() {
		return true;
	}
	
	
	public double getTiempoNecesario() {
		double tiempo = 0;
		for (Atraccion atrac : atraccionesIncluidas){
			tiempo += atrac.getTiempoNecesario();
		}
		return tiempo;
	}

	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", costoDeVisita=" + costo + ", tiempoNecesario="
				+ tiempoNecesario + ", "+ "tipo=" + tipo + "]";
	}
	
	// arreglar metodo
	public void restarCupo() {
		this.cupoPersonas--;
		
	}
	

	
}
