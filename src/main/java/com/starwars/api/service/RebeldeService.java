package com.starwars.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starwars.api.model.Item;
import com.starwars.api.model.Rebelde;
import com.starwars.api.model.dto.RebeldeItemDTO;
import com.starwars.api.repository.RebeldeRepository;
import com.starwars.api.service.util.RebeldeServiceUtils;

@Service
public class RebeldeService {
	
	@Autowired
	private RebeldeRepository rebeldes;
	
	@Autowired
	private RebeldeServiceUtils utils;
	
	public List<Rebelde> listar() {
		return rebeldes.findAll();
	}
	
	public Rebelde salvar(Rebelde rebelde) {
		if(rebelde.getDenuncia() >= 3)
			rebelde.setTraidor(true);
		
		return rebeldes.save(rebelde);
	}
	
	public Rebelde trocarItens(RebeldeItemDTO rebeldeInical, RebeldeItemDTO rebeldeFinal) {
		utils.comparaPontos(rebeldeInical, rebeldeFinal);
		
		Rebelde reb1 = this.procurarPorId(rebeldeInical.getId());
		Rebelde reb2 = this.procurarPorId(rebeldeFinal.getId());

		utils.verificaTraidor(reb1);
		utils.verificaTraidor(reb2);
		
		utils.verificaSeItemExiste(rebeldeInical.getInventario(), reb1.getInventario());
		utils.verificaSeItemExiste(rebeldeFinal.getInventario(), reb2.getInventario());
		
		List<Item> itensTemp = new ArrayList<>();
		itensTemp = rebeldeInical.getInventario();
		
		utils.removeItens(reb1.getInventario(), rebeldeInical.getInventario());
		utils.adicionaItens(reb1.getInventario(), rebeldeFinal.getInventario());		

		utils.removeItens(reb2.getInventario(), rebeldeFinal.getInventario());
		utils.adicionaItens(reb2.getInventario(), itensTemp);		
				
		rebeldes.save(reb1);
		rebeldes.save(reb2);
				
		return reb1;
	}
	
	
	public Rebelde procurarPorId(Long id) {
		Rebelde rebelde = utils.verificaSeExiste(id);
		return rebelde;
	}
	
}
