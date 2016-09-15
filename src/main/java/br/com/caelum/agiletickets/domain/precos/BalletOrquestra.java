package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class BalletOrquestra {
	
	public static BigDecimal calculaPreco(Sessao sessao) {
		BigDecimal preco;
		Integer totalIngressos = sessao.getTotalIngressos();
		BigDecimal precoSessao = sessao.getPreco();
		Integer ingressosReservados = sessao.getIngressosReservados();
		if((totalIngressos - ingressosReservados) / totalIngressos.doubleValue() <= 0.50) { 
			preco = precoSessao.add(precoSessao.multiply(BigDecimal.valueOf(0.20)));
		} else {
			preco = precoSessao;
		}
		
		if(sessao.getDuracaoEmMinutos() > 60){
			preco = preco.add(precoSessao.multiply(BigDecimal.valueOf(0.10)));
		}
		return preco;
	}

}
