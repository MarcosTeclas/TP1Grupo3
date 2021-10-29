package parque;

public abstract class Producto {

	protected int id;
	protected String nombre;
	protected double costo;
	protected double tiempoNecesario;
	protected int cupoPersonas;
	protected TipoDeAtraccion tipo;
	
	public Producto() {
	}
	
	//constructor promo porcentual
	public Producto(int id, String nombre, TipoDeAtraccion tipo){
		this.tipo = tipo;
		this.nombre = nombre;
	}
	
	//constructor para promo absoluta
	public Producto(int id, String nombre, TipoDeAtraccion tipo, int costo){
		this.tipo = tipo;
		this.nombre = nombre;
		this.costo = costo;
	}
	
	//contructor de atracciones
	public Producto(int id, String nombre, double costo, double tiempoNecesario, int cupoPersonas, TipoDeAtraccion tipo) {
		this.nombre = nombre;
		this.costo = costo;
		this.tiempoNecesario = tiempoNecesario;
		this.cupoPersonas = cupoPersonas;
		this.tipo = tipo;
	}

	public abstract boolean esPromo();
	public abstract double getCosto();
	public abstract double getTiempoNecesario();
	public abstract void restarCupo();
	public abstract String toStringParaMostrar();
	public abstract boolean contiene(Producto producto);
	
	public TipoDeAtraccion getTipo() {
		return tipo;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public boolean hayCupo() {
		return this.cupoPersonas > 0;
	}
	
	
	
	@Override
	public String toString() {
		return "Nombre: " + nombre + ", costo: " + costo + ", tiempo necesario: "
				+ tiempoNecesario + ", cupo de personas: " + cupoPersonas + ", tipo: " + tipo;
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
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}	
}
