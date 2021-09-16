package parque;

	import static org.junit.Assert.*;

	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.List;

	import org.junit.Test;

	public class ComparatorTest {

		@Test
		public void crearOrdenarTest() {
			ComparablePorAtraccionPreferida comparable = new ComparablePorAtraccionPreferida(null);

			assertNotNull(comparable);
		}

		@Test
		public void ordenCorrectoTest() {
			ComparablePorAtraccionPreferida comparable  = new ComparablePorAtraccionPreferida(TipoDeAtraccion.AVENTURA);
			Atraccion atraccion1 = new Atraccion("Erebor", 12, 6, 1, TipoDeAtraccion.PAISAJE);
			Atraccion atraccion2 = new Atraccion("Abismo de Helm", 12, 2, 15, TipoDeAtraccion.DEGUSTACION);
			Atraccion atraccion3 = new Atraccion("Minas Tirith", 10, 2.5, 25, TipoDeAtraccion.AVENTURA);
			Atraccion atraccion4 = new Atraccion("Lothloriem", 35, 1, 30, TipoDeAtraccion.DEGUSTACION);
			Atraccion atraccion5 = new Atraccion("Mordor", 10, 3, 4, TipoDeAtraccion.AVENTURA);
			Atraccion[] misAtracciones = {atraccion1, atraccion2, atraccion3, atraccion4, atraccion5};
			
			List<Atraccion> atraccionesAventura = new ArrayList<Atraccion>();
			atraccionesAventura.add(atraccion3);
			atraccionesAventura.add(atraccion5);
			List<Atraccion> atraccionesDegustacion = new ArrayList<Atraccion>();
			atraccionesDegustacion.add(atraccion2);
			atraccionesDegustacion.add(atraccion4);
			Promocion promo1 = new PromoAbsoluta("Pack Degustacion", TipoDeAtraccion.DEGUSTACION, 36, atraccionesDegustacion);
			Promocion promo2 = new PromoPorcentual("Pack Aventura", TipoDeAtraccion.AVENTURA, 20, atraccionesAventura);
			
			List<Producto> ordenObtenido = new ArrayList<Producto>();
			ordenObtenido.addAll(Arrays.asList(misAtracciones));
			ordenObtenido.add(promo1);
			ordenObtenido.add(promo2);
			ordenObtenido.sort(comparable);
			List<Producto> ordenEsperado = new ArrayList<Producto>();
			ordenEsperado.add(promo2);
			ordenEsperado.add(promo1);
			ordenEsperado.add(atraccion5);
			ordenEsperado.add(atraccion3);
			ordenEsperado.add(atraccion4);
			ordenEsperado.add(atraccion1);
			ordenEsperado.add(atraccion2);
			
			assertEquals(ordenEsperado, ordenObtenido);
		}
	}

