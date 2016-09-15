package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		
		TipoDeEspetaculo tipoEspetaculo = sessao.getEspetaculo().getTipo();
		if(tipoEspetaculo.equals(TipoDeEspetaculo.CINEMA) || tipoEspetaculo.equals(TipoDeEspetaculo.SHOW)) {
			//quando estiver acabando os ingressos... 
			preco = CinemaShow.calculaPreco(sessao);
		} else if(tipoEspetaculo.equals(TipoDeEspetaculo.BALLET) || tipoEspetaculo.equals(TipoDeEspetaculo.ORQUESTRA)) {
			preco = BalletOrquestra.calculaPreco(sessao);
		} else {
			//nao aplica aumento para teatro (quem vai é pobretão)
			preco = sessao.getPreco();
		} 

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

}