package parque;

import java.util.Comparator;

public class ComparablePorAtraccionPreferida implements Comparator<Producto> {

	private TipoDeAtraccion atraccionPreferida;

	public ComparablePorAtraccionPreferida(TipoDeAtraccion atraccionPreferida) {
		this.atraccionPreferida = atraccionPreferida;
	}

	@Override
	public int compare(Producto o1, Producto o2) {
		if (o1.tipo == this.atraccionPreferida && o2.tipo == this.atraccionPreferida) {
			// ambas son preferidas, compara por lo siguiente (promo)
			if (o1.esPromo() && o2.esPromo()) {
				// ambas son promos, compara por costo
				if (Double.compare(o1.costo, o2.costo) == 0) {
					// mismo costo, comparo por tiempo finalmente
					return -Double.compare(o1.tiempoNecesario, o2.tiempoNecesario);
				} else {
					return -Double.compare(o1.costo, o2.costo);
				}
			} else if (!o1.esPromo() && !o2.esPromo()) {
				// ninguna es promo, compara por costo
				if (Double.compare(o1.costo, o2.costo) == 0) {
					// mismo costo, comparo por tiempo finalmente
					return -Double.compare(o1.tiempoNecesario, o2.tiempoNecesario);
				} else {
					return -Double.compare(o1.costo, o2.costo);
				}
			} else {
				return -Boolean.compare(o1.esPromo(), o2.esPromo());
			}
		} else if (o1.tipo != this.atraccionPreferida && o2.tipo != this.atraccionPreferida){
			if (o1.esPromo() && o2.esPromo()) {
				// ambas son promos, compara por costo
				if (Double.compare(o1.costo, o2.costo) == 0) {
					// mismo costo, comparo por tiempo finalmente
					return -Double.compare(o1.tiempoNecesario, o2.tiempoNecesario);
				} else {
					return -Double.compare(o1.costo, o2.costo);
				}
			} else if (!o1.esPromo() && !o2.esPromo()) {
				// ninguna es promo, compara por costo
				if (Double.compare(o1.costo, o2.costo) == 0) {
					// mismo costo, comparo por tiempo finalmente
					return -Double.compare(o1.tiempoNecesario, o2.tiempoNecesario);
				} else {
					return -Double.compare(o1.costo, o2.costo);
				}
			} else {
				return -Boolean.compare(o1.esPromo(), o2.esPromo());
			}
		} else {
			// una es preferida y la otra no
			if (o1.tipo == this.atraccionPreferida) return -1;
			return 1;
		}
	}
}