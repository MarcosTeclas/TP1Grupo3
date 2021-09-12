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
		this.atraccionesIncluidas = new ArrayList<Atraccion>();
	}
	
	//constructor para promo absoluta
	public Promocion(String nombre, TipoDeAtraccion tipo, int costo, List<Atraccion> atraccionesIncluidas) {
		super(nombre, tipo, costo);
		this.atraccionesIncluidas = new ArrayList<Atraccion>();
	}
	


	@Override
	public boolean esPromo() {
		return true;
	}
	

	
}
