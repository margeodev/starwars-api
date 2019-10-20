package com.starwars.api.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Localizacao {
	
	private Double latitude;
	private Double longitude;
	private String nomeLocalizacao;
	
//	########## GETTERS / SETTERS ##########		
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	@Column(name = "nome_localizacao")
	public String getNomeLocalizacao() {
		return nomeLocalizacao;
	}
	public void setNomeLocalizacao(String nomeLocalizacao) {
		this.nomeLocalizacao = nomeLocalizacao;
	}
	
	
}
