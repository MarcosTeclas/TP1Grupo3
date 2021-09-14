package parque;

import java.util.List;

public class PromoAbsoluta extends Promocion {

	// falta aplicarle el descuento

	public PromoAbsoluta() {
		super();
	}

	public PromoAbsoluta(String nombre, TipoDeAtraccion tipo, int costo, List<Atraccion> atraccionesIncluidas) {
		super(nombre, tipo, costo, atraccionesIncluidas);
		this.tiempoNecesario = super.getTiempoNecesario();
	}

	@Override
	public double getCosto() {
		return this.costo;
	}
}
