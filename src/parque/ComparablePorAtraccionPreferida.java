package parque;

import java.util.Comparator;

public class ComparablePorAtraccionPreferida implements Comparator<Producto> {

	private TipoDeAtraccion atraccionPreferida;

	public ComparablePorAtraccionPreferida() {
	}

	public ComparablePorAtraccionPreferida(TipoDeAtraccion atraccionPreferida) {
		this.atraccionPreferida = atraccionPreferida;
	}

	@Override
	public int compare(Producto o1, Producto o2) {

		boolean sonPreferidasLasDosAtracciones = o1.tipo == atraccionPreferida && o2.tipo == atraccionPreferida;
		boolean sonPromosLasDosAtracciones = o1.esPromo() && o2.esPromo();
		boolean tienenElMismoCosto = Double.compare(o1.costo, o2.costo) == 0;

		if (sonPreferidasLasDosAtracciones) {
			if (sonPromosLasDosAtracciones) {
				return comparadorPorCosto(o1, o2, tienenElMismoCosto);// comparo por costo y por tiempo
			} else {
				return comparadorPorPromos(o1, o2);// y sino comparo por promo
			}
		} else if (!sonPreferidasLasDosAtracciones) {
			if (sonPromosLasDosAtracciones) {
				return comparadorPorCosto(o1, o2, tienenElMismoCosto);
			} else if (!sonPromosLasDosAtracciones) {
				if (tienenElMismoCosto) {
					return comparadorPorCosto(o1, o2, tienenElMismoCosto);// comparo por costo y por tiempo
				} else {
					return comparadorPorPromos(o1, o2);// y sino comparo por promo
				}
			}
		} else {
			if (o1.tipo == atraccionPreferida) {
				return -1;
			}
		}
		return 1;

	}

	private int comparadorPorPromos(Producto o1, Producto o2) {
		return Boolean.compare(o1.esPromo(), o2.esPromo()) * -1;
	}

	private int comparadorPorCosto(Producto o1, Producto o2, boolean tienenElMismoCosto) {
		if (tienenElMismoCosto) {
			return comparadorPorTiempo(o1, o2); // comparo por tiempo
		} else {
			return Double.compare(o1.costo, o2.costo) * -1;// y sino comparo por costo
		}
	}

	private int comparadorPorTiempo(Producto o1, Producto o2) {
		return Double.compare(o1.tiempoNecesario, o2.tiempoNecesario) * -1;
	}
}