package br.rmc.loteria.xml;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import br.rmc.loteria.domain.ConcursoInfo;
import br.rmc.loteria.domain.Loteria;
import br.rmc.loteria.main.Constants;
import br.rmc.xml.XMLHandler;

public class MegaSenaXMLHandler extends XMLHandler {
	
	private Loteria megaSena = new Loteria();
	
	private List<String> listaPropriedade;
	
	private ConcursoInfo concurso;
	
	/* (non-Javadoc)
	 * @see br.rmc.xml.XMLHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, tag, attributes);
		
		if (Constants.TAG_LOTERIA.equals(tag)) {
			// faz nada.
		} else if (Constants.TAG_PROPRIEDADES.equals(tag)) {
			this.listaPropriedade = new LinkedList<String>();
		} else if (Constants.TAG_PROPRIEDADE.equals(tag)) {
			// faz nada.
		} else if (Constants.TAG_NOME.equals(tag)) {
			// faz nada.
		} else if (Constants.TAG_CONCURSO_INFO.equals(tag)) {
			concurso = new ConcursoInfo(this.listaPropriedade);
		} else {
			
		}
		
	}

	/* (non-Javadoc)
	 * @see br.rmc.xml.XMLHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String tag) throws SAXException {
		if (Constants.TAG_LOTERIA.equals(tag)) {
			// faz nada.
		} else if (Constants.TAG_PROPRIEDADES.equals(tag)) {
			// faz nada.
		} else if (Constants.TAG_PROPRIEDADE.equals(tag)) {
			if (this.getValorAtual() != null) {
				this.listaPropriedade.add(this.getValorAtual());
			}
		} else if (Constants.TAG_NOME.equals(tag)) {
			megaSena.setNome(this.getValorAtual());
		} else if (Constants.TAG_CONCURSO_INFO.equals(tag)) {
			megaSena.addConcurso(this.concurso);
		} else {
			this.concurso.setValue(this.getNomeTagAtual(), this.getValorAtual());
		}
		
		
		super.endElement(uri, localName, tag);
	}



	@Override
	protected String getNomeXmlHandler() {
		return "MegaSena";
	}

	/**
	 * @return the megaSena
	 */
	public Loteria getLoteria() {
		return megaSena;
	}
	
}
