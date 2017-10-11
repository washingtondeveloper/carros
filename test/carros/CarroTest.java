package carros;

import br.com.livro.domain.CarroService;
import junit.framework.TestCase;

public class CarroTest extends TestCase {
	
	public void testFindCarroById() {
		CarroService service = new CarroService();
		Long id = 1L;
		assertTrue(service.getCarroById(id).getId() == id);
		assertEquals(service.findByName("Tucker 1948").get(0).getNome(), "Tucker 1948");
		assertEquals(service.findByTipo("luxo").get(0).getTipo(), "luxo");
	}
}
