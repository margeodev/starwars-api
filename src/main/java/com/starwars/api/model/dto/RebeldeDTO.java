package com.starwars.api.model.dto;

import com.starwars.api.model.Localizacao;

public class RebeldeDTO {

	private Localizacao localizacao;
	private int denuncia;
	
	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public int getDenuncia() {
		return denuncia;
	}
	public void setDenuncia(int denuncia) {
		this.denuncia = denuncia;
	}
		
}
