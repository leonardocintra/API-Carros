package test;

import java.util.List;

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;
import junit.framework.TestCase;

public class CarroTest extends TestCase {
	private CarroService carroService = new CarroService();
	
	public void testListaCarros(){
		List<Carro> carros = carroService.getCarros();
		assertNotNull(carros);
		// valida se encontrou algo
		assertTrue(carros.size() > 0);
		// valida se encontrou o Tucker
		Carro tucker = carroService.findByName("Tucker 1948").get(0);
		assertEquals("Tucker 1948", tucker.getNome());
		// valida se encontrou a Ferrari
		Carro ferrari = carroService.findByName("Ferrari FF").get(0);
		assertEquals("Ferrari FF", ferrari.getNome());
		// valida se encontrou a Bugatti
		Carro bugatti = carroService.findByName("Bugatti Veyron").get(0);
		assertEquals("Bugatti Veyron", bugatti.getNome());		
	}
	
	public void testSalvarDeletarCarro(){
		Carro c = new Carro();
		c.setNome("Teste");
		c.setDesc("Teste desc");
		c.setUrlFoto("url foto aqui");
		c.setUrlFoto("url vidio aqui");
		c.setLatitude("la3829389");
		c.setLongitude("loi382938");
		c.setTipo("que tipo");
		carroService.save(c);
		// id do carro salvo
		Long id = c.getId();
		assertNotNull(id);
		// busca no banco de dados para confirmar se o carro foi salvo
		c = carroService.getCarro(id);
		assertEquals("Teste", c.getNome());
		assertEquals("la3829389", c.getLatitude());
		// atualiza o carro
		c.setNome("Teste UPDATE");
		carroService.save(c);
		c = carroService.getCarro(id);
		assertEquals("Teste UPDATE", c.getNome());
		assertEquals("la3829389", c.getLatitude());
		// deleta o carro
		carroService.delete(id);
		// busca o carro novamente
		c = carroService.getCarro(id);
		assertNull(c);	
	}
}
