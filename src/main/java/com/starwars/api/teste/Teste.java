package com.starwars.api.teste;

import java.util.ArrayList;
import java.util.List;

import javax.management.StringValueExp;

import com.starwars.api.model.Genero;
import com.starwars.api.model.Item;
import com.starwars.api.model.Rebelde;

public class Teste {
	public static void main(String[] args) {
		Rebelde r1 = novoRebelde1();
		Rebelde r2 = novoRebelde2();
		Rebelde r3 = novoRebelde3();
		
		List<Rebelde> rebeldes = new ArrayList<>();
		rebeldes.add(r1);
		rebeldes.add(r2);
		rebeldes.add(r3);
		
		double armas = 0;
		double municoes = 0;
		double aguas = 0;
		double comidas = 0;
		
		for(Rebelde rebelde : rebeldes) {
			for(Item item : rebelde.getInventario()) {
				if(item == Item.ARMA)
					armas++;
				
				if(item == Item.MUNICAO)
					municoes++;
				
				if(item == Item.AGUA)
					aguas++;
				
				if(item == Item.COMIDA)
					comidas++;
			}
		}
		
		
		
		System.out.println(armas/rebeldes.size());		
		System.out.println(municoes/rebeldes.size());		
		System.out.println(aguas/rebeldes.size());		
		System.out.println(comidas/rebeldes.size());		
	}
	
	
	public static Rebelde novoRebelde1() {		
		Rebelde rebelde = new Rebelde();
		rebelde.setNome("aaa");
		rebelde.setInventario(geraInventario1());
		
		return rebelde;
	}

	public static Rebelde novoRebelde2() {		
		Rebelde rebelde = new Rebelde();
		rebelde.setNome("bbb");
		rebelde.setInventario(geraInventario2());
		
		return rebelde;
	}
	
	public static Rebelde novoRebelde3() {		
		Rebelde rebelde = new Rebelde();
		rebelde.setNome("ccc");
		rebelde.setInventario(geraInventario3());
		
		return rebelde;
	}
	
	private static List<Item> geraInventario1() {
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

	private static List<Item> geraInventario2() {
		List<Item> inventario = new ArrayList<>();
		Item item1 = Item.ARMA;
		Item item2 = Item.MUNICAO;
		Item item3 = Item.COMIDA;
		inventario.add(item1);
		inventario.add(item2);
		inventario.add(item3);
		
		return inventario;
		
	}
	private static List<Item> geraInventario3() {
		List<Item> inventario = new ArrayList<>();
		Item item1 = Item.ARMA;
		Item item2 = Item.MUNICAO;
		Item item3 = Item.COMIDA;
		Item item4 = Item.AGUA;
		inventario.add(item1);
		inventario.add(item2);
		inventario.add(item3);
		inventario.add(item3);
		inventario.add(item3);
		
		return inventario;
		
	}
}
