package br.rmc.loteria.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class Loteria {
	
	private String nome;
	
	private List<ConcursoInfo> listaConcurso;
	
	

	public Loteria() {
		super();
	}

	/**
	 * @param listaConcurso
	 */
	public Loteria(String n, List<ConcursoInfo> lista) {
		super();
		if (StringUtils.isBlank(n)) {
			throw new IllegalArgumentException("Nome eh obrigatorio!!");
		}
		
		this.setNome(n);
		this.setListaConcurso(lista);
	}
	
	
	public String toXML() {
		StringBuilder builder = new StringBuilder();
		builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n\n");
		
		builder.append("<").append(getClass().getSimpleName().toLowerCase()).append(">\n\n");
		
		List<String> fields = this.getListaConcurso().get(0).getFields();
		builder.append("<").append("propriedades").append(">\n");
		for (String field : fields) {
			builder.append("<").append("propriedade").append(">");
			builder.append(field);
			builder.append("</").append("propriedade").append(">\n");			
		}
		builder.append("</").append("propriedades").append(">\n\n");
		
		builder.append("<").append("nome").append(">");
		builder.append(this.getNome());
		builder.append("</").append("nome").append(">\n\n");
		
		
		builder.append("<").append("concursoinfos").append(">\n\n");
		for (ConcursoInfo resultadoLoteria : this.getListaConcurso()) {
			builder.append(resultadoLoteria.toXML()).append("\n");
		}
		builder.append("</").append("concursoinfos").append(">\n\n");
		
		builder.append("</").append(getClass().getSimpleName().toLowerCase()).append(">\n");
		
		String returnValue = builder.toString().replaceAll("1_dezena", "dezena_1");
		returnValue = returnValue.replaceAll("2_dezena", "dezena_2");
		returnValue = returnValue.replaceAll("3_dezena", "dezena_3");
		returnValue = returnValue.replaceAll("4_dezena", "dezena_4");
		returnValue = returnValue.replaceAll("5_dezena", "dezena_5");
		returnValue = returnValue.replaceAll("6_dezena", "dezena_6");
		
		return returnValue;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getNome() {
		return nome;
	}
	
	/**
	 * @return the listaConcurso
	 */
	protected List<ConcursoInfo> getListaConcurso() {
		if (this.listaConcurso == null) {
			this.listaConcurso = new LinkedList<ConcursoInfo>();
		}
		return this.listaConcurso;
	}

	/**
	 * @param listaConcurso the listaConcurso to set
	 */
	protected void setListaConcurso(List<ConcursoInfo> lista) {
		if (lista == null || lista.isEmpty()) {
			throw new IllegalArgumentException("Lista de Concursos eh obrigatiro!!");
		}
		this.listaConcurso = lista;
	}
	
	public void addConcurso(ConcursoInfo concurso) {
		if (concurso != null) {
			this.getListaConcurso().add(concurso);
		}
	}


	/**
	 * Busca por concurso que tenham dezenas iguais ao concurso de busca.<br>
	 * O concurso de busca deve ter dezenas com tamanho menor ou igual ao tamanho de dezenas do concurso.<br>
	 * Retorna o primeiro elemento encontrado.
	 * 
	 * @param conjunto
	 * @return
	 */
	public ConcursoInfo findConcurso(ConjuntoDezena conjunto) {
		ConcursoInfo concurso = null;
		
		for (ConcursoInfo c : this.listaConcurso) {
			if (c.isEqualsConjuntoDezena(conjunto)) {
				concurso = c;
				break;
			}
		}
		
		return concurso;
	}

	/**
	 * Busca por concursos que tenham todas as dezenas da lista de busca.<br>
	 * A lista de busca deve ter dezenas com tamanho menor ou igual ao tamanho de dezenas do concurso.<br>
	 * Retorna o primeiro elemento encontrado.
	 * 
	 * @param listParcial
	 * @return
	 */
	public ConcursoInfo containsConjuntoDezena(List<String> listParcial) {
		ConcursoInfo concurso = null;
		
		for (ConcursoInfo c : this.listaConcurso) {
			if (c.containsConjuntoDezena(listParcial)) {
				concurso = c;
				break;
			}
		}
		
		return concurso;
	}

	
	/**
	 * Retorna a dezena que mais aparece.<br>
	 * 
	 * @return
	 */
	public String dezenaMaisAparece() {
		Map<String, Integer> mapContagem = new HashMap<String, Integer>();
		Integer count;
		for (ConcursoInfo c : this.listaConcurso) {
			Iterator<String> dezenas = c.iteratorDezenas();
			while (dezenas.hasNext()) {
				String d = (String) dezenas.next();
				if (mapContagem.containsKey(d)) {
					count = mapContagem.get(d);
					count++;
				} else {
					count = 1;					
				}
				mapContagem.put(d, count);
			}//While iterator dezenas
		}// For lista concursos
		
		String dezena = null;
		Integer maiorTotal = 0;
		Set<String> listaDosMais = mapContagem.keySet();
		
		List<String> listaOrdenada = new ArrayList<String>(listaDosMais.size());
		listaOrdenada.addAll(listaDosMais);
		
		Collections.sort(listaOrdenada);
		
		for (String num : listaOrdenada) {
			Integer total = mapContagem.get(num);
			System.out.println(num + " : " + total);
			
			if (total > maiorTotal) {
				maiorTotal = total;
				dezena = num;
			}
			
		}
		
		
		return dezena;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder(30);
		
		strBuilder.append(this.getNome()).append(" - ");
		strBuilder.append("Total de Concursos: ").append(this.getListaConcurso().size());
		
		return strBuilder.toString();	
	}

}
