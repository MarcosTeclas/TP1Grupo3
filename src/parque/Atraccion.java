package parque;

public class Atraccion extends Producto{
	
	public boolean ocuparAtraccion;

	public Atraccion() {
		super();
	}

	public Atraccion(String nombre, double costo,double tiempoNecesario, int cupoPersonas, TipoDeAtraccion tipo) {
		super(nombre, costo, tiempoNecesario, cupoPersonas, tipo);
	}
	

	public boolean quedaCupo() {
		return this.cupoPersonas>0;
	}
	
	public void ocuparAtraccion() {
		if (quedaCupo()) {
			this.cupoPersonas--;
		}
	}
	
	@Override
	public boolean esPromo() {
		return false;
	}
	
	@Override
	public String toString() {
		return "Atraccion: " + nombre + ", costo: " + costo + ", tiempo necesario: " + tiempoNecesario
				+ ", tipo de Atracción=" + tipo;
	}

	@Override
	public double getCosto() {
		return this.costo;
	}

	@Override
	public double getTiempoNecesario() {
		return this.tiempoNecesario;
	}

	@Override
	public void restarCupo() {
		this.cupoPersonas--;
		
	}

	
	

	

}
