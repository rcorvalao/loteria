package br.rmc.loteria.domain;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConjuntoDezenaTest {
	
	private ConjuntoDezena conjuntoDezena;
	
	@Before
	public void beforeTest() {
		System.out.println("before test");
	}
	
	@After
	public void afterTest() {
		System.out.println("after test");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstrucaoNull() {
		new ConjuntoDezena(null);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstrucaoListaVazia() {
		new ConjuntoDezena(new LinkedList<String>());
	}
	
	@Test
	public void testConstrucaoComUmElemento() {
		new ConjuntoDezena(Arrays.asList("01"));
	}

}
