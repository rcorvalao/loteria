package br.rmc.loteria.combinacao;

import org.junit.Assert;
import org.junit.Test;

public class CombinacaoCalculoTest {
	
	
	@Test
	public void testCombinacao60agrupados6() {
		
		Double r = CombinacaoCalculo.combinacao(60, 6);
		
		Assert.assertTrue(r == 50063859.999999456d);
		
	}

}
