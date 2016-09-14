package br.com.caelum.agiletickets.domain;

import java.util.List;

import br.com.caelum.agiletickets.models.Estabelecimento;
import br.com.caelum.agiletickets.models.Sessao;

public interface DiretorioDeSessoes {

	List<Sessao> todos();

	void adiciona(Sessao sessao);

}
