package parque;

import java.util.List;

public class PromoAbsoluta extends Promocion {

	public PromoAbsoluta(String nombre, TipoDeAtraccion tipo, double costo, List<Atraccion> atraccionesIncluidas) {
		super(tipo, nombre, atraccionesIncluidas);
		this.costo = getCosto();
		this.tiempoNecesario = super.getTiempoNecesario();
	}
	
	@Override
	public double getCosto() {
		return this.costo;
	}

}
