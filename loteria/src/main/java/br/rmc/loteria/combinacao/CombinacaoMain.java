package br.rmc.loteria.combinacao;

/**
 * 
 * @author daemonio
 */
public class CombinacaoMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] str = { "uva", "pera", "laranja", "manga", "goiaba" };
		String[] saida;

		/**
		 * Combinacoes de 5 elementos agrupados de 3 em 3.
		 */
		Combinacao comb1 = new Combinacao(str, 3);
		while (comb1.hasNext()) {
			saida = comb1.next();
			System.out.println(saida[0] + "," + saida[1] + "," + saida[2]);
		}

		/**
		 * Todas as combinacoes de qualquer tamanho. Nesse caso, passe 0 (zero)
		 * no segundo parametro.
		 */
		System.out.println("**************************");
		Combinacao comb2 = new Combinacao(str, 0);
		while (comb2.hasNext()) {
			saida = comb2.next();
			// Itera em todos os elementos da combinacao
			for (String e : saida) {
				System.out.print(e + ",");
			}
			System.out.println();
		}

	}

}
