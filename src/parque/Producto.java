package parque;

public abstract class Producto {
	protected String nombre;
	protected double costo;
	protected double tiempoNecesario;
	protected int cupoPersonas;
	protected TipoDeAtraccion tipo;
	
	public Producto() {
	}
	
	//constructor promo porcentual
	public Producto(String nombre, TipoDeAtraccion tipo){
		this.tipo = tipo;
		this.nombre = nombre;
	}
	
	//constructor para promo absoluta
	public Producto(String nombre, TipoDeAtraccion tipo, int costo){
		this.tipo = tipo;
		this.nombre = nombre;
		this.costo = costo;
	}
	
	//contructor de atracciones
	public Producto(String nombre, double costo, double tiempoNecesario, int cupoPersonas, TipoDeAtraccion tipo) {
		this.nombre = nombre;
		this.costo = costo;
		this.tiempoNecesario = tiempoNecesario;
		this.cupoPersonas = cupoPersonas;
		this.tipo = tipo;
	}

	public abstract boolean esPromo();		
	public abstract double getCosto();
	public abstract double getTiempoNecesario();
	
	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", costoDeVisita=" + costo + ", tiempoNecesario="
				+ tiempoNecesario + ", CupoPersonasPorDia=" + cupoPersonas + ", tipo=" + tipo + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (Double.doubleToLongBits(costo) != Double.doubleToLongBits(other.costo))
			return false;
		if (cupoPersonas != other.cupoPersonas)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (Double.doubleToLongBits(tiempoNecesario) != Double.doubleToLongBits(other.tiempoNecesario))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

	
}
