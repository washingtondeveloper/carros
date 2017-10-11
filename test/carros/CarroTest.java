package carros;

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;
import junit.framework.TestCase;

public class CarroTest extends TestCase {
	
	public void testFindCarroById() {
		Carro c = null;
			c = new CarroService().getCarroById(1L);
			assertTrue(c.getId() == 1);
		
	}
}
