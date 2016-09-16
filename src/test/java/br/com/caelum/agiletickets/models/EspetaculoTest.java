package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}
	
	
	@Test
	public void deveCriarUmaUnicaSessaoSeOInicioForIgualAoFim() {
		// Arrange
		Espetaculo teste = new Espetaculo();
		LocalDate inicio = new LocalDate(2016,9,15);
		LocalDate fim = inicio;		
		LocalTime horario = new LocalTime(23,0);
		
		// Ac
		List<Sessao> sessoes = teste.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		//Assert
		Assert.assertNotNull(sessoes);
		Assert.assertEquals(1, sessoes.size());
		Sessao sessao = sessoes.get(0);
		Assert.assertEquals("15/09/16", sessao.getDia());
	}

	@Test
	public void deveCriarSessoesDiarias() {
		// Arrange
		Espetaculo teste = new Espetaculo();
		LocalDate inicio = new LocalDate(2016,9,15);
		LocalDate fim = new LocalDate(2016,9,16);		
		LocalTime horario = new LocalTime(23,0);
		
		// Act
		List<Sessao> sessoes = teste.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		//Assert
		Assert.assertNotNull(sessoes);
		Assert.assertEquals(2, sessoes.size());
		Assert.assertEquals("15/09/16", sessoes.get(0).getDia());
		Assert.assertEquals("16/09/16", sessoes.get(1).getDia());
	}
	
	@Test
	public void deveCriarSessoesSemanais() {
		// Arrange
		Espetaculo teste = new Espetaculo();
		LocalDate inicio = new LocalDate(2010,1,1);
		LocalDate fim = new LocalDate(2010,1,31);		
		LocalTime horario = new LocalTime(17,0);
		
		// Act
		List<Sessao> sessoes = teste.criaSessoes(inicio, fim, horario, Periodicidade.SEMANAL);
		
		//Assert
		Assert.assertNotNull(sessoes);
		Assert.assertEquals(6, sessoes.size());
		Assert.assertEquals("01/01/10", sessoes.get(0).getDia());
		Assert.assertEquals("08/01/10", sessoes.get(1).getDia());
		Assert.assertEquals("15/01/10", sessoes.get(2).getDia());
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
}
