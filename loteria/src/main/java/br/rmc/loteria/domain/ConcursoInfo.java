package br.rmc.loteria.domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.rmc.loteria.main.Constants;

public class ConcursoInfo {

	private List<String> fields;
	private Map<String, String> mapValues;
	
	private ConjuntoDezena conjuntoDezena;
	private List<String> listaFieldDezena;
	
	public ConcursoInfo(final List<String> osFields) {
		super();
		if (osFields == null || osFields.isEmpty()) {
			throw new IllegalArgumentException("Lista de Campos esta vazia!!!");
		}
		this.setFields(osFields);
	}
	
	/**
	 * Retorna um iterator da lista de dezenas deste Conjunto.<br>
	 * 
	 * 
	 * @return
	 */
	public Iterator<String> iteratorDezenas() {
		return this.conjuntoDezena.iteratorDezenas();
	}
	
	public void setValue(String fieldName, String value) {
		if (this.getFields().contains(fieldName)) {
			this.getMapValues().put(fieldName, value);
		} else {
			throw new IllegalStateException("Nao eh uma Propriedade valida para este Obejto: " + fieldName);
		}
	}
	
	/**
	 * @param fields
	 *            the fields to set
	 */
	private void setFields(final List<String> fields) {
		this.fields = fields;
	}

	/**
	 * @return the fields
	 */
	public List<String> getFields() {
		if (this.fields == null) {
			throw new IllegalStateException("A lista de Propriedades ainda nao foi inicializada.");
		}
		return fields;
	}

	public String toXML() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("<").append(getClass().getSimpleName().toLowerCase()).append(">\n");

		for (String fieldName : this.getFields()) {
			strBuilder.append("<").append(fieldName).append(">");
			strBuilder.append(this.getValue(fieldName));
			strBuilder.append("</").append(fieldName).append(">\n");			
		}
		
		strBuilder.append("</").append(getClass().getSimpleName().toLowerCase()).append(">\n");
		return strBuilder.toString();
	}
	
	public String getNumeroConcurso() {
		return this.getValue(Constants.FIELD_CONCURSO);
	}

	protected String getValue(String fieldName) {
		return this.getMapValues().get(fieldName);
	}

	/**
	 * @return the mapValues
	 */
	protected Map<String, String> getMapValues() {
		if (this.mapValues == null) {
			this.mapValues = new HashMap<String, String>();
		}
		return this.mapValues;
	}
	
	public boolean containsConjuntoDezena(List<String> listParcial) {
		return getConjuntoDezena().containsConjuntoDezena(listParcial);
	}
	
	public boolean isEqualsConjuntoDezena(ConjuntoDezena conjunto) {
		return getConjuntoDezena().isEqualsConjuntoDezena(conjunto);
	}

	private ConjuntoDezena getConjuntoDezena() {
		if (this.conjuntoDezena == null) {
			this.conjuntoDezena = this.loadConjuntoDezena();
		}
		return this.conjuntoDezena;
	}

	private ConjuntoDezena loadConjuntoDezena() {
		List<String> lista = new LinkedList<String>();
		
		for (String fieldName : this.getListaFieldDezena()) {
			String dezenaValue = this.getValue(fieldName);
			lista.add(dezenaValue);
		}
		
		return new ConjuntoDezena(lista);
	}
	
	private List<String> getListaFieldDezena() {
		if (this.listaFieldDezena == null) {
			this.listaFieldDezena = new LinkedList<String>();
			for (String fieldName : this.getFields()) {
				if (fieldName.contains(Constants.FIELD_DEZENA)) {
					this.listaFieldDezena.add(fieldName);
				}
			}			
		}
		
		return this.listaFieldDezena;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder(30);
		
		strBuilder.append("Concurso: ");
		strBuilder.append(this.getNumeroConcurso());
		strBuilder.append(" - ").append(this.getConjuntoDezena().toString());
		
		return strBuilder.toString();
	}

	
	
}
