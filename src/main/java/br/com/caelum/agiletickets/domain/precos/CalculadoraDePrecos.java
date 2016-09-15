package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		
		TipoDeEspetaculo tipoEspetaculo = sessao.getEspetaculo().getTipo();
		Integer totalIngressos = sessao.getTotalIngressos();
		BigDecimal precoSessao = sessao.getPreco();
		Integer ingressosReservados = sessao.getIngressosReservados();
		if(tipoEspetaculo.equals(TipoDeEspetaculo.CINEMA) || tipoEspetaculo.equals(TipoDeEspetaculo.SHOW)) {
			//quando estiver acabando os ingressos... 
			preco = calculaPrecoCinemaShow(totalIngressos, precoSessao,
					ingressosReservados);
		} else if(tipoEspetaculo.equals(TipoDeEspetaculo.BALLET) || tipoEspetaculo.equals(TipoDeEspetaculo.ORQUESTRA)) {
			preco = calculaPrecoBalletOrquestra(sessao, totalIngressos,
					precoSessao, ingressosReservados);
		} else {
			//nao aplica aumento para teatro (quem vai é pobretão)
			preco = precoSessao;
		} 

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private static BigDecimal calculaPrecoBalletOrquestra(Sessao sessao,
			Integer totalIngressos, BigDecimal precoSessao,
			Integer ingressosReservados) {
		BigDecimal preco;
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

	private static BigDecimal calculaPrecoCinemaShow(Integer totalIngressos,BigDecimal precoSessao, Integer ingressosReservados) {
		BigDecimal preco;
		if((totalIngressos - ingressosReservados) / totalIngressos.doubleValue() <= 0.05) { 
			preco = precoSessao.add(precoSessao.multiply(BigDecimal.valueOf(0.10)));
		} else {
			preco = precoSessao;
		}
		return preco;
	}

}