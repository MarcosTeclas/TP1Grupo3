package parque;

import java.util.Comparator;

public class ComparablePorAtraccionPreferida implements Comparator<Producto> {

	private TipoDeAtraccion atraccionPreferida;

	public ComparablePorAtraccionPreferida(TipoDeAtraccion atraccionPreferida) {
		this.atraccionPreferida = atraccionPreferida;
	}

	@Override
	public int compare(Producto o1, Producto o2) {
		boolean sonPreferidasLasDosAtracciones = o1.tipo.equals(atraccionPreferida) && o2.tipo.equals(atraccionPreferida);
		boolean sonDistintasLasDosAtracciones = !o1.tipo.equals(atraccionPreferida) && !o2.tipo.equals(atraccionPreferida);
		boolean sonPromosLasDosAtracciones = o1.esPromo() && o2.esPromo();
		boolean noSonPromosLasDosAtracciones = !o1.esPromo() && !o2.esPromo();
		boolean tienenElMismoCosto = Double.compare(o1.costo, o2.costo) == 0;
		
		if (sonPreferidasLasDosAtracciones) {
			if (sonPromosLasDosAtracciones) {
				return comparadorPorCostoYtiempo(o1, o2, tienenElMismoCosto);
			} else if (noSonPromosLasDosAtracciones) {
				return comparadorPorCostoYtiempo(o1, o2, tienenElMismoCosto);
			} else {
				return comparadorPorPromos(o1, o2);
			}
		} else if (sonDistintasLasDosAtracciones){
			if (sonPromosLasDosAtracciones) {
				return comparadorPorCostoYtiempo(o1, o2, tienenElMismoCosto);
			} else if (noSonPromosLasDosAtracciones) {
				return comparadorPorCostoYtiempo(o1, o2, tienenElMismoCosto);
			} else {
				return comparadorPorPromos(o1, o2);
			}
		} else {
			if (o1.tipo == this.atraccionPreferida) return -1;
			return 1;
		}
	}

	private int comparadorPorCostoYtiempo(Producto o1, Producto o2, boolean tienenElMismoCosto) {
		if (tienenElMismoCosto) {
			return comparadorPorTiempo(o1, o2);
		} else {
			return comparadorPorCosto(o1, o2);
		}
	}
	
	private int comparadorPorPromos(Producto o1, Producto o2) {
		return Boolean.compare(o1.esPromo(), o2.esPromo()) * -1;
	}
	private int comparadorPorCosto(Producto o1, Producto o2) {
		return Double.compare(o1.costo, o2.costo) * -1;
	}

	private int comparadorPorTiempo(Producto o1, Producto o2) {
		return Double.compare(o1.tiempoNecesario, o2.tiempoNecesario) * -1;
	}
}