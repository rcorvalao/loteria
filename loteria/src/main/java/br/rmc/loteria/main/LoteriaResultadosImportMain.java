package br.rmc.loteria.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import br.rmc.loteria.domain.ConcursoInfo;
import br.rmc.loteria.domain.Loteria;

/**
 * Responsavel por Importar os Resultados da Mega-Sena 
 * fornecida pelo Caixa Economica Federal
 * para o Formato do Sistema.
 * 
 * @author rcorvalao
 *
 */
public class LoteriaResultadosImportMain {
	
	
	public static void main(String[] args) throws Exception {
		System.out.println("Iniciando Importacao....");
		
		
		String fileName = Constants.PATH_FILES + "/D_MEGA.HTM";
		
		File file = new File(fileName);
		InputStream fileInputStream = new FileInputStream(file); 		
		Source source = new Source(fileInputStream);
		
		System.out.println("Carregou arquivo.");
		List<String> listPropetyName = null;
		
		List<ConcursoInfo> listResultadoLoteria = new LinkedList<ConcursoInfo>();
		List<Element> elementList = source.getAllElements(HTMLElementName.TR);
		System.out.println("Tranformando Informacoes.....");
		for (int i = 0; i < elementList.size(); i++) {
			Element element = elementList.get(i);
			
			if (i == 0) {
				// Identificacao do Cabecalho.
				List<Element> elementListHeader = element.getAllElements(HTMLElementName.TH);
				listPropetyName = new LinkedList<String>(); 
				for (Element property : elementListHeader) {
					String propetyName = property.getContent().getTextExtractor().toString();
					if (!StringUtils.isNumeric(propetyName)) {
						propetyName = substituirEspacoseAcentos(propetyName);
						propetyName = StringUtils.lowerCase(propetyName);
						listPropetyName.add(propetyName);						
					} else {
						listPropetyName = new LinkedList<String>();
						continue;
					}
				}
				continue;
			}
			
			if (listPropetyName != null && !listPropetyName.isEmpty()) {
				// Se identificou um Cabecalho.
				ConcursoInfo resultadoLoteria = new ConcursoInfo(listPropetyName);
				
				List<Element> elementListValue = element.getAllElements(HTMLElementName.TD);
				
				// IF comparacao do tamanho das informacoes
				if (listPropetyName.size() == elementListValue.size()) {
					//for (Element property : elementListValue) {
					for (int j = 0; j < elementListValue.size(); j++) {
						Element property = elementListValue.get(j);
						String propertyValue = property.getContent().getTextExtractor().toString();
						resultadoLoteria.setValue(listPropetyName.get(j), propertyValue);
					}// FOR TDs
				} else {
					throw new IllegalStateException("Quantidade de Propriedades nao condiz a quantidade de Valores.");
				}
				
				listResultadoLoteria.add(resultadoLoteria);
			} else {
				// Caso nao tenha identificado um a cabecalho.
				throw new IllegalStateException("Nao identificou um Cabecalho!!!!");
				
			}
			
		}// FOR TRs
		System.out.println("Transformacao de informacoes completa.");
		
		
		Loteria megaSena = new Loteria("MegaSena", listResultadoLoteria);
		
		
		System.out.println("Gerando XML de saida...");
		File fileOut = new File(Constants.PATH_FILES + File.separator + Constants.FILE_RESULT_XML);
		if (fileOut.exists()) {
			System.out.println("Gerando backup do XML antigo ...");
			File fileOutBkp = new File(Constants.PATH_FILES + File.separator + Constants.FILE_RESULT_XML_BKP);
			byte[] arrayByte = FileUtils.readFileToByteArray(fileOut);
			FileUtils.writeByteArrayToFile(fileOutBkp, arrayByte);
			System.out.println("backup feito.");
		}
		FileUtils.write(fileOut, megaSena.toXML());
		System.out.println("XML gerado.");
		
		
	}

	private static String substituirEspacoseAcentos(String string) {
		String str = string.replaceAll("�", "a");
		str = str.replaceAll("�", "e");
		str = str.replaceAll("�", "i");
		str = str.replaceAll("�", "o");
		str = str.replaceAll("�", "u");
		
		str = str.replaceAll("�", "A");
		str = str.replaceAll("�", "E");
		str = str.replaceAll("�", "I");
		str = str.replaceAll("�", "O");
		str = str.replaceAll("�", "U");

		str = str.replaceAll("�", "a");
		str = str.replaceAll("�", "e");
		str = str.replaceAll("�", "i");
		str = str.replaceAll("�", "o");
		str = str.replaceAll("�", "u");
		
		str = str.replaceAll("�", "A");
		str = str.replaceAll("�", "E");
		str = str.replaceAll("�", "I");
		str = str.replaceAll("�", "O");
		str = str.replaceAll("�", "U");
		
		str = str.replaceAll("�", "a");
		str = str.replaceAll("�", "A");

		str = str.replaceAll("�", "a");
		str = str.replaceAll("�", "A");
		
		str = str.replaceAll("�", "");
		str = str.replaceAll("�", "");

		str = str.replaceAll("�", "c");
		str = str.replaceAll("�", "C");

		str = str.replaceAll("�", "");
		
		str = str.replaceAll(" ", "_");

		return str;
	}

}
