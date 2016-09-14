package br.com.caelum.agiletickets.persistencia;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.agiletickets.domain.DiretorioDeSessoes;
import br.com.caelum.agiletickets.models.Sessao;

public class JPASessaoDao implements DiretorioDeSessoes {

	private EntityManager manager;
	
	/** @deprecated CDI eyes only*/
	protected JPASessaoDao() {
	}
	
	@Inject
	public JPASessaoDao(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public List<Sessao> todos() {
		return manager.createQuery("select s from Sessao s", Sessao.class).getResultList();
	}

	@Override
	public void adiciona(Sessao sessao) {
		manager.persist(sessao);
	}

}
