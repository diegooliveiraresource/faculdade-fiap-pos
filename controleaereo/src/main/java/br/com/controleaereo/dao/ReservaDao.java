package br.com.controleaereo.dao;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.controleaereo.bean.Assento;
	
@Repository
@SuppressWarnings("unchecked")
public class ReservaDao extends SessionFac implements GenericDAO<Assento> {

	private static ReservaDao INSTANCE;

	private ReservaDao() {
		if (INSTANCE == null) {
			INSTANCE = this;
		}
	}

	public static ReservaDao getInstance() {
		return INSTANCE;
	}

	@Override
	public List<Assento> listAll() {
		List<Assento> assentos = (List<Assento>)getSession().createQuery("from Assento").list();
		return assentos;
	}

	@Override
	public Assento find(Long id) {
		Assento c = (Assento) getSession().get(Assento.class, id);
		return c;
	}

	@Override
	public Assento update(Assento t) {
		getSession().update(t);
		return t;
	}
	
	public void update(List<Assento> assentos) {
		for (Assento assento : assentos) {
			getSession().update(assento);
		}
	}

	@Override
	public Assento save(Assento t) throws Exception{
		getSession().save(t);
		return t;
	}

	@Override
	public void delete(Assento t) {
		getSession().delete(t);
	}

}
