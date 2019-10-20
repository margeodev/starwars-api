package com.starwars.api.model.dto;

import java.util.List;

import com.starwars.api.model.Item;

public class RebeldeItemDTO {

	private Long id;
	private List<Item> inventario;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public List<Item> getInventario() {
		return inventario;
	}
	public void setInventario(List<Item> inventario) {
		this.inventario = inventario;
	}
	
}
