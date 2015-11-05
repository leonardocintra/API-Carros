package br.com.livro.domain;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unchecked")
public class CarroDAO extends HibernateDAO<Carro> {
	public CarroDAO() {
		// informa o tipo da entidade para o Hibernate
		super(Carro.class);
	}

	public Carro getCarroById(Long id) {
		// o Hibernate consulta automaticamente pelo id
		return super.get(id);
	}

	public List<Carro> findByName(String nome) {
		Query q = getSession().createQuery("from Carro where lower(nome) like lower(?)");
		q.setString(0, "%" + nome + "%");
		return q.list();
	}

	public List<Carro> findByTipo(String tipo) {
		Query q = getSession().createQuery("from Carro where tipo = ?");
		q.setString(0, tipo);
		List<Carro> carros = q.list();
		return carros;
	}

	public List<Carro> getCarros() {
		Query q = getSession().createQuery("from Carro");
		List<Carro> carros = q.list();
		return carros;
	}

	public void salvar(Carro c) {
		super.save(c);
	}

	public boolean delete(Long id) {
		Carro carro = get(id);
		delete(carro);
		return true;
	}
}
