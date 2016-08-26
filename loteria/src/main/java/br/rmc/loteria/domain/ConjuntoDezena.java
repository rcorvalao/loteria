package br.rmc.loteria.domain;

import java.util.Iterator;
import java.util.List;

public class ConjuntoDezena {
	
	
	private List<String> listaDezenas;

	/**
	 * @param listaDezenas
	 */
	public ConjuntoDezena(List<String> lista) {
		super();
		if (lista == null || lista.isEmpty()) {
			throw new IllegalArgumentException("Lista de dezenas vazia!!");
		}
		this.listaDezenas = lista;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((listaDezenas == null) ? 0 : listaDezenas.hashCode());
		return result;
	}

	/**
	 * Compara a lista com o equals do List.
	 * Soh é igual quando sao os mesmos valores na ordem.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ConjuntoDezena)) {
			return false;
		}
		ConjuntoDezena other = (ConjuntoDezena) obj;
		if (listaDezenas == null) {
			if (other.listaDezenas != null) {
				return false;
			}
		} else if (!listaDezenas.equals(other.listaDezenas)) {
			return false;
		}
		return true;
	}

	/**
	 * Compara a lista de dezenas de cada conjunto.<br>
	 * Devem ter a mesma quantidade de dezenas.<br>
	 * Tendo os mesmos valores independente da ordem.
	 * 
	 * @param conjunto
	 * @return
	 */
	public boolean isEqualsConjuntoDezena(ConjuntoDezena conjunto) {
		if (conjunto == null) {
			throw new IllegalArgumentException("Conjunto de dezenas nulo.");
		}
			
		if (conjunto.listaDezenas.size() != this.listaDezenas.size()) {
			throw new IllegalArgumentException("Conjuntos da comparação não tem o mesmo tamanho.");
		}
		
		return this.listaDezenas.containsAll(conjunto.listaDezenas);
	}
	
	/**
	 * Compara a lista deste conjunto com a lista do parametro.<br>
	 * 
	 * A lista de parametros de busca deve ser menor ou igual ao tamanho do conjunto.
	 * 
	 * @param dezenas
	 * @return
	 */
	public boolean containsConjuntoDezena(List<String> dezenas) {
		if (dezenas == null || dezenas.isEmpty()) {
			throw new IllegalArgumentException("Lista de busca nula ou vazia.");
		}
			
		if (dezenas.size() > this.listaDezenas.size()) {
			throw new IllegalArgumentException("Lista de busca é maior que o Conjunto de Dezenas.");
		}
		
		return this.listaDezenas.containsAll(dezenas);
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder(30);
		strBuilder.append(this.listaDezenas);
		return strBuilder.toString();
	}

	/**
	 * Retorna um iterator da lista de dezenas deste Conjunto.<br>
	 * 
	 * @return
	 */
	public Iterator<String> iteratorDezenas() {
		return this.listaDezenas.iterator();
	}
	

}
