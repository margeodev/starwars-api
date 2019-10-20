package com.starwars.api.util;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starwars.api.model.Genero;
import com.starwars.api.model.Item;
import com.starwars.api.model.Localizacao;
import com.starwars.api.model.Rebelde;
import com.starwars.api.model.dto.RebeldeItemDTO;

public class TestesUtils {
	
	public static final String NOME = "Paulswith";
	public static final int IDADE = 89;
	public static List<Item> INVENTARIO = new ArrayList<>();

			
	public static Rebelde novoRebelde() {		
		Rebelde rebelde = new Rebelde();
		rebelde.setNome("Paulswith");
		rebelde.setIdade(89);
		rebelde.setGenero(Genero.MASCULINO);
		rebelde.setInventario(geraInventario());
		rebelde.setLocalizacao(geraLocalizacao());
		rebelde.setTraidor(false);
		rebelde.setDenuncia(0);
		
		return rebelde;
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	
	public static Localizacao alteraLocalizacao() {
		Localizacao localizacao = new Localizacao();
		localizacao.setLatitude(13.416203);
		localizacao.setLongitude(15.66283);
		localizacao.setNomeLocalizacao("Tatooine");
		
		return localizacao;
		
	}
	
	public static RebeldeItemDTO[] oberListaItemDeRebeldesParaTrocar() {
		RebeldeItemDTO[] lista = new RebeldeItemDTO[2];
		
		RebeldeItemDTO r1 = new RebeldeItemDTO();
		RebeldeItemDTO r2 = new RebeldeItemDTO();
		
		r1.setId(1L);
		r1.setInventario(primeiraListaItensRebelde());
		r2.setId(3L);
		r2.setInventario(segundaListaItensRebelde());
		
		lista[0] = r1;
		lista[1] = r2;
		
		return lista;
	}

	public static RebeldeItemDTO[] oberListaItemDeTraidoresParaTrocar() {
		RebeldeItemDTO[] lista = new RebeldeItemDTO[2];
		
		RebeldeItemDTO r1 = new RebeldeItemDTO();
		RebeldeItemDTO r2 = new RebeldeItemDTO();
		
		r1.setId(1L);
		r1.setInventario(primeiraListaItensTraidor());
		r2.setId(3L);
		r2.setInventario(terceiraListaItensRebelde());
		
		lista[0] = r1;
		lista[1] = r2;
		
		return lista;
	}
	
	private static List<Item> primeiraListaItensRebelde() {
		List<Item> inventario = new ArrayList<>();
		Item item1 = Item.MUNICAO;
		Item item2 = Item.AGUA;
		inventario.add(item1);
		inventario.add(item1);
		inventario.add(item2);

		return inventario;
	}

	private static List<Item> segundaListaItensRebelde() {
		List<Item> inventario = new ArrayList<>();
		Item item1 = Item.ARMA;
		Item item2 = Item.AGUA;
		inventario.add(item1);
		inventario.add(item2);
		inventario.add(item2);
		
		return inventario;
	}
	
	private static List<Item> primeiraListaItensTraidor() {
		List<Item> inventario = new ArrayList<>();
		Item item = Item.AGUA;
		inventario.add(item);
		return inventario;
	}

	private static List<Item> terceiraListaItensRebelde() {
		List<Item> inventario = new ArrayList<>();
		Item item = Item.COMIDA;
		inventario.add(item);
		inventario.add(item);
		return inventario;
	}
	
	private static Localizacao geraLocalizacao() {
		Localizacao localizacao = new Localizacao();
		localizacao.setLatitude(23.417203);
		localizacao.setLongitude(15.66283);
		localizacao.setNomeLocalizacao("Bosthirda");
		
		return localizacao;
		
	}
	
	private static List<Item> geraInventario() {
		List<Item> inventario = new ArrayList<>();
		Item item1 = Item.ARMA;
		Item item2 = Item.MUNICAO;
		Item item3 = Item.COMIDA;
		Item item4 = Item.AGUA;
		inventario.add(item1);
		inventario.add(item2);
		inventario.add(item3);
		inventario.add(item4);
		
		return inventario;
		
	}

}

