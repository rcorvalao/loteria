package br.rmc.loteria.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import br.rmc.loteria.domain.ConcursoInfo;
import br.rmc.loteria.domain.ConjuntoDezena;
import br.rmc.loteria.domain.Loteria;
import br.rmc.loteria.xml.MegaSenaXMLHandler;

public class LoteriaMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Loteria main - inicio ....");
		
		
		try {
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			InputStream inputStream = new FileInputStream(Constants.PATH_FILES + File.separator + Constants.FILE_RESULT_XML);
			Reader reader = new InputStreamReader(inputStream,"UTF-8");
			InputSource aInputSource = new InputSource(reader);
			
			//DefaultHandler 
			MegaSenaXMLHandler handler = new MegaSenaXMLHandler();
			parser.parse(aInputSource, handler);
			Loteria megaSena = handler.getLoteria();
			
			
			
			
			System.out.println(megaSena);
			
			
			List<String> lista = Arrays.asList("01", "23", "36", "43", "47", "59");
			ConjuntoDezena conjunto = new ConjuntoDezena(lista);
			List<String> lista1 = Arrays.asList("47", "59", "01", "23", "36", "43");
			ConjuntoDezena conjunto1 = new ConjuntoDezena(lista1);
			
			
			ConcursoInfo concurso = megaSena.findConcurso(conjunto);
			System.out.println("resultado-758-semOrdem: " + concurso);
			ConcursoInfo concurso1 = megaSena.findConcurso(conjunto1);
			System.out.println("resultado-758-naOrdem: " + concurso1);
			
			
			List<String> listParcial1 = Arrays.asList("09", "14", "21");		
			ConcursoInfo concurso2 = megaSena.containsConjuntoDezena(listParcial1);
			System.out.println("resultado-naoEncontrado: " + concurso2);
			
			List<String> listParcial = Arrays.asList("23", "36", "59");		
			ConcursoInfo concurso3 = megaSena.containsConjuntoDezena(listParcial);
			System.out.println("resultado-758-parcial: " + concurso3);
			
			List<String> listParcial11 = Arrays.asList("19","35","15","22","34","16");		
			ConcursoInfo concurso31 = megaSena.containsConjuntoDezena(listParcial11);
			System.out.println("resultado-891: " + concurso31);

			List<String> listMeuCartao = Arrays.asList("07", "08", "09", "14", "21", "52");		
			ConcursoInfo concursoMeuCartao = megaSena.containsConjuntoDezena(listMeuCartao);
			System.out.println("concursoMeuCartao: " + concursoMeuCartao);
			
			
			List<List<String>> listaQuadra = new LinkedList<List<String>>();
			listaQuadra.add(Arrays.asList("07", "08", "09", "14"));
			listaQuadra.add(Arrays.asList("07", "08", "09", "21"));
			listaQuadra.add(Arrays.asList("07", "08", "09", "52"));
			listaQuadra.add(Arrays.asList("07", "08", "14", "21"));
			listaQuadra.add(Arrays.asList("07", "08", "14", "52"));
			listaQuadra.add(Arrays.asList("07", "14", "21", "52"));
			listaQuadra.add(Arrays.asList("07", "14", "21", "09"));
			listaQuadra.add(Arrays.asList("14", "21", "52", "08"));
			listaQuadra.add(Arrays.asList("14", "21", "52", "09"));			
			for (List<String> list : listaQuadra) {
				ConcursoInfo concursoQ = megaSena.containsConjuntoDezena(list);
				System.out.println("concursoQuadra: " + concursoQ);
			}
			
			List<List<String>> listaQuina = new LinkedList<List<String>>();
			listaQuina.add(Arrays.asList("07", "08", "09", "14", "21"));
			listaQuina.add(Arrays.asList("07", "08", "09", "14", "52"));
			listaQuina.add(Arrays.asList("07", "08", "09", "52", "21"));
			listaQuina.add(Arrays.asList("07", "08", "52", "14", "21"));
			listaQuina.add(Arrays.asList("07", "52", "09", "14", "21"));
			listaQuina.add(Arrays.asList("52", "08", "09", "14", "21"));
			for (List<String> list : listaQuina) {
				ConcursoInfo concursoQ = megaSena.containsConjuntoDezena(list);
				System.out.println("concursoQuina: " + concursoQ);
			}


			String mais = megaSena.dezenaMaisAparece();
			System.out.println("dezena q mais aparece: " + mais);
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Loteria main - fim.");
	}

}
