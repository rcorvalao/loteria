package br.rmc.xml;

import org.apache.commons.lang.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Manipulacao de XML com SAX.
 * 
 * @author rcorvalao
 *
 */
public abstract class XMLHandler extends DefaultHandler {
	
    private StringBuffer galhoAtual = new StringBuffer(200);
    
    private StringBuffer valorTagAtual = new StringBuffer(100);
    
    private String nomeTagAtual;

	protected abstract String getNomeXmlHandler();

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 */
	@Override
	public void endDocument() throws SAXException {
		System.out.println(getNomeXmlHandler() + " endDocument.");
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 */
	@Override
	public void startDocument() throws SAXException {
		System.out.println(getNomeXmlHandler() + " startDocument ...");
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		//super.characters(ch, start, length);
		getValorTagAtual().append(ch, start, length);
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String tag) throws SAXException {
		//System.out.println("endElement: " + tag + " localName: " + localName + " uri: " + uri);
		
		getGalhoAtual().delete(  
		        galhoAtual.length() - tag.length() - 1,  
		        galhoAtual.length()
		);
		
		getValorTagAtual().delete(0, getValorTagAtual().length());
		
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {		
		getGalhoAtual().append("/" + tag);
		this.setNomeTagAtual(tag);
		
		System.out.println("galho: " + getGalhoAtual().substring(1));
	}


	

	/**
	 * @return the galhoAtual
	 */
	private StringBuffer getGalhoAtual() {
		return galhoAtual;
	}

	/**
	 * @return the nomeTagAtual
	 */
	protected String getNomeTagAtual() {
		return nomeTagAtual;
	}

	/**
	 * @param nomeTagAtual the nomeTagAtual to set
	 */
	private void setNomeTagAtual(String nomeTagAtual) {
		this.nomeTagAtual = nomeTagAtual;
	}

	/**
	 * @return the valorTagAtual
	 */
	private StringBuffer getValorTagAtual() {
		return valorTagAtual;
	}
	
	/**
	 * Retorna o valor da tag atual com String.
	 * 
	 * @return
	 */
	protected String getValorAtual() {
		String returnValue = getValorTagAtual().toString();
		if (returnValue != null) {
			returnValue = returnValue.trim();
		}
		if (StringUtils.isBlank(returnValue)) {
			returnValue = null;
		}
		return returnValue;
	}
	
}
