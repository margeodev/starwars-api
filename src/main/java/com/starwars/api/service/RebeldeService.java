package com.starwars.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starwars.api.model.Item;
import com.starwars.api.model.Rebelde;
import com.starwars.api.model.Relatorio;
import com.starwars.api.model.dto.RebeldeItemDTO;
import com.starwars.api.repository.RebeldeRepository;
import com.starwars.api.service.exception.RecursoNaoEncontradoException;
import com.starwars.api.service.util.RebeldeServiceUtils;


@Service
public class RebeldeService {
	
	@Autowired
	private RebeldeRepository rebeldes;
	
	@Autowired
	private RebeldeServiceUtils utils;
	
	public List<Rebelde> listarTodos() {
		return rebeldes.findAll();
	}
	public List<Rebelde> listarRebeldes() {
		return rebeldes.findAllByTraidor(false);
	}

	public List<Rebelde> listarTraidores() {
		return rebeldes.findAllByTraidor(true);
	}
	
	public Rebelde salvar(Rebelde rebelde) {
		if(rebelde.getDenuncia() >= 3) {
			rebelde.setTraidor(true);
		} else {
			rebelde.setTraidor(false);
		}
		
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
	
	public Relatorio geraRelatorios() {
		double rebeldes = this.listarRebeldes().size();
		double traidores = this.listarTraidores().size();
		double total = rebeldes + traidores;
		
		String percentualRebeldes = utils.calculaPercentual(rebeldes, total);
		String percentualTraidores = utils.calculaPercentual(traidores, total);
		
		Relatorio relatorios = new Relatorio();
		
		int totalPontosPerdidos = utils.calculaPontosPerdidos(this.listarTraidores());
		
		List<String> mediaItens = utils.calculaMediaDeItensPorRebelde(this.listarRebeldes());
		
		relatorios.setRebeldes(percentualRebeldes);
		relatorios.setTraidores(percentualTraidores);
		relatorios.setPontosPerdidos(totalPontosPerdidos);
		relatorios.setMediaItens(mediaItens);	
		
		return relatorios;
	}
	
	public Rebelde procurarPorId(Long id) {
		Optional<Rebelde> rebelde = rebeldes.findById(id);
		if (!rebelde.isPresent())
			throw new RecursoNaoEncontradoException("Não existe um rebelde com o id informado");

		return rebelde.get();
	}
	
	
	public void excluir(Long id) {
		@SuppressWarnings("unused")
		Rebelde rebelde = this.procurarPorId(id);		
		this.rebeldes.deleteById(id);		
	}
	
}
