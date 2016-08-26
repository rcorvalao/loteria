package br.rmc.loteria.combinacao;

import org.apache.commons.math3.util.ArithmeticUtils;


/**
 * Combinações
 * Quando formamos agrupamentos com p elementos, (p<m) de forma que os p elementos sejam distintos entre sí apenas pela espécie.
 * 
 * Combinação simples: Não ocorre a repetição de qualquer elemento em cada grupo de p elementos.
 * 
 * Fórmula: C(m,p) = m!/[(m-p)! p!]
 * Cálculo para o exemplo: C(4,2)=4!/[2!2!]=24/4=6
 * 
 * Exemplo: Seja C={A,B,C,D}, m=4 e p=2. As combinações simples desses 4 elementos tomados 2 a 2 são 6 grupos que não podem ter a repetição de qualquer elemento nem podem aparecer na ordem trocada. Todos os agrupamentos estão no conjunto:
 * 
 * Cs={AB,AC,AD,BC,BD,CD}
 * 
 * 
 * @author Izabel Lopes
 *
 */
public class CombinacaoCalculo {
	
	/**
	 * Fórmula: C(m,p) = m!/[(m-p)! p!]
	 * 
	 * @param m
	 * @param p
	 * @return
	 */
	public static Double combinacao(Integer m, Integer p) {
		Double fatM = ArithmeticUtils.factorialDouble(m);
		Double fatP = ArithmeticUtils.factorialDouble(p);
		
		Integer mMenosP = m - p;
		Double fatMmenosP = ArithmeticUtils.factorialDouble(mMenosP);
		
		Double result = fatM / (fatMmenosP * fatP) ;
				
		return result;
	}
	
	
	
	

}
