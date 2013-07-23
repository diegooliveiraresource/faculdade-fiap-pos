package br.com.controleaereo.bo;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.controleaereo.bean.Assento;
import br.com.controleaereo.bean.Usuario;
import br.com.controleaereo.bean.Voo;
import br.com.controleaereo.dao.ReservaDao;
import br.com.controleaereo.dao.VooDao;

@Service
public class VooBO {

	@Inject
	private VooDao vooDao;

	private ReservaDao reservaDao;

	private static VooBO INSTANCE;

	private VooBO() {
		if (INSTANCE == null) {
			INSTANCE = this;
		}
	}

	public static VooBO getInstance() {
		return INSTANCE;
	}

	public void cadastra(Voo voo) throws Exception {
		vooDao.save(voo);
	}

	public List<Voo> recuperaVoos() {
		List<Voo> voos = vooDao.listAll();
		return voos;
	}

	public Voo recuperaVoo(Long id) {
		return vooDao.find(id);
	}

	public void reservar(Usuario u, Voo voo) {
		reservaDao.update(voo.getAssentos());
	}

	public List<Assento> recuperaAssentosDisponiveis(Usuario u, Long idVoo) {
		return vooDao.findAssentos(u.getId(), idVoo);
	}

}