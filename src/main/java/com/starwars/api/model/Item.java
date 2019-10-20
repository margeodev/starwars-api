package com.starwars.api.model;

public enum Item {
	ARMA(4),
	MUNICAO(3),
	AGUA(2),
	COMIDA(1);
	
	private final int pontos;
	
	private Item(int pontos) {
        this.pontos = pontos;
    }
	
	public int getPontos() {
        return this.pontos;
    }
	
}
