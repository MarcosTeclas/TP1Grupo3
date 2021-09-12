package parque;

import java.util.List;

public class PromoAxB extends Promocion {
	
	//falta aplicarle el descuento

	public PromoAxB() {
		super();
	}

	public PromoAxB(TipoDeAtraccion tipo, String nombre, List<Atraccion> atraccionesIncluidas) {
		super(tipo, nombre, atraccionesIncluidas);
	}
	
	@Override
	public double getCosto() {
		return this.costo;
	}

	@Override
	public double getTiempoNecesario() {
		double tiempo = 0;
		for (Atraccion atrac : atraccionesIncluidas){
			tiempo += atrac.getTiempoNecesario();
		}
		return tiempo;
	}

}
