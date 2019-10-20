package com.starwars.api.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tb_rebelde")
public class Rebelde {

	private Long id;
	private String nome;
	private int idade;
	private Genero genero;
	private Localizacao localizacao;
	private List<Item> inventario;
	private boolean traidor;
	private int denuncia;
	
	
//	########## GETTERS / SETTERS ##########
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
		
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	@Enumerated(EnumType.STRING)
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	@Embedded
	public Localizacao getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}
	
//	@ElementCollection(targetClass=Item.class)
//    @Enumerated(EnumType.ORDINAL) 
//    @CollectionTable(name="tb_rebelde_itens")
//    @Column(name="item_id") 
	
	@ElementCollection(targetClass = Item.class)
	@CollectionTable(name = "tb_rebelde_item", joinColumns = @JoinColumn(name = "rebelde_id"))
	@Column(name = "item_descricao")
	@Enumerated(EnumType.STRING)
	public List<Item> getInventario() {
		return inventario;
	}
	public void setInventario(List<Item> inventario) {
		this.inventario = inventario;
	}	
	
	public boolean isTraidor() {
		return traidor;
	}
	public void setTraidor(boolean traidor) {
		this.traidor = traidor;
	}
	
	public int getDenuncia() {
		return denuncia;
	}
	public void setDenuncia(int denuncia) {
		this.denuncia = denuncia;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rebelde other = (Rebelde) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
