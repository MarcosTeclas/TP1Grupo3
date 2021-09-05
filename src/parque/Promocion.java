package parque;

import java.util.LinkedList;
import java.util.List;

public abstract class Promocion extends Producto {

	private List<Atraccion> atraccionesIncluidas;

	public Promocion() {
		super();
	}

	public Promocion(TipoDeAtraccion tipo, String nombre, double costoDeVisita, double tiempoNecesario,
			int cupoPersonasPorDia, List<Atraccion> atraccionesIncluidas) {
		super(tipo, nombre, costoDeVisita, tiempoNecesario, cupoPersonasPorDia);
		atraccionesIncluidas = new LinkedList<Atraccion>();
	}

	@Override
	public boolean esPromo() {
		return true;
	}
}
