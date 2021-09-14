package parque;

import java.util.List;
import java.util.ListIterator;

public class PromoAxB extends Promocion {

	// falta aplicarle el descuento

	public PromoAxB() {
		super();
	}

	public PromoAxB(TipoDeAtraccion tipo, String nombre, List<Atraccion> atraccionesIncluidas) {
		super(tipo, nombre, atraccionesIncluidas);
		this.costo = getCosto();
		this.tiempoNecesario=super.getTiempoNecesario();
	}

	@Override
	public double getCosto() {
		double costo = 0;
		ListIterator<Atraccion> itr = atraccionesIncluidas.listIterator(1);
		while (itr.hasNext()) {
			costo += itr.next().getCosto();
		}
		return costo;
	}

}
