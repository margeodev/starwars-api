package com.starwars.api.model;

import java.util.List;

public class Relatorio {

	private String rebeldes;
	private String traidores;
	private int pontosPerdidos;
	private List<String> mediaItens;
	
	public String getRebeldes() {
		return rebeldes;
	}
	public void setRebeldes(String rebeldes) {
		this.rebeldes = rebeldes;
	}
	public String getTraidores() {
		return traidores;
	}
	public void setTraidores(String traidores) {
		this.traidores = traidores;
	}
	public int getPontosPerdidos() {
		return pontosPerdidos;
	}
	public void setPontosPerdidos(int pontosPerdidos) {
		this.pontosPerdidos = pontosPerdidos;
	}
	public List<String> getMediaItens() {
		return mediaItens;
	}
	public void setMediaItens(List<String> mediaItens) {
		this.mediaItens = mediaItens;
	}
			
}
