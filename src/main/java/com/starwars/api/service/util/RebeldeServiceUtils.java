package com.starwars.api.service.util;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starwars.api.model.Item;
import com.starwars.api.model.Rebelde;
import com.starwars.api.model.dto.RebeldeItemDTO;
import com.starwars.api.repository.RebeldeRepository;
import com.starwars.api.service.exception.BadRequest;
import com.starwars.api.service.exception.RecursoNaoEncontradoException;

@Service
public class RebeldeServiceUtils {

	private static final String NAO_ENCONTRADO = "Rebelde não localizado.";
	private static final String PONTUACAO_INCOMPATIVEL = "A pontuação dos itens não é compatível.";
	private static final String ITEM_NAO_ENCONTRADO = "Item não localizado no inventário.";
	private static final String REBELDE_TRAIDOR = "Inventário bloqueado para rebeldes traidores.";

	@Autowired
	private RebeldeRepository rebeldes;

	/**
	 * Busca um rebelde com o id informado.
	 * 
	 * @param id do rebelde pesquisado.
	 * @return Se existir um rebelde com o id informado, será retornado, caso não
	 *         exista, será lançada uma exceção.
	 */
	public final Rebelde verificaSeExiste(Long id) {
		Optional<Rebelde> rebelde = rebeldes.findById(id);
		if (!rebelde.isPresent())
			throw new RecursoNaoEncontradoException(NAO_ENCONTRADO);

		return rebelde.get();
	}

	/**
	 * Veririca se o somatório dos pontos das listas de itens dos rebeldes passados
	 * no parâmetro são iguais. Caso o somatório dos pontos não sejam iguais, será
	 * lançada uma exceção.
	 * 
	 * @param
	 */
	public final void comparaPontos(RebeldeItemDTO rebeldeOrigem, RebeldeItemDTO rebeldeDestino) {
		int toalPontosRebeldeOrigem = 0;
		int toalPontosRebeldeDestino = 0;

		for (Item item : rebeldeOrigem.getInventario()) {
			toalPontosRebeldeOrigem += item.getPontos();
		}

		for (Item item : rebeldeDestino.getInventario()) {
			toalPontosRebeldeDestino += item.getPontos();
		}

		if (toalPontosRebeldeOrigem != toalPontosRebeldeDestino) {
			throw new BadRequest(PONTUACAO_INCOMPATIVEL);
		}
	}

	/**
	 * Remove os itens (listaFinal) de uma lista de itens (listaInicial).
	 * 
	 * @param
	 * @return lista de itens atualizada
	 */
	public final List<Item> removeItens(List<Item> listaInicial, List<Item> listaFinal) {
		for (Item item : listaFinal) {
			listaInicial.remove(item);
		}
		return listaInicial;
	}

	/**
	 * Adiciona itens (listaFinal) em uma lista de itens (listaInicial).
	 * 
	 * @param
	 * @return lista de itens atualizada
	 */
	public final List<Item> adicionaItens(List<Item> listaInicial, List<Item> listaFinal) {
		for (Item item : listaFinal) {
			listaInicial.add(item);
		}
		return listaInicial;
	}

	public final void verificaSeItemExiste(List<Item> listaInicial, List<Item> listaFinal) {
		List<Item> listaMenor = this.verificaListaMenor(listaInicial, listaFinal);
		List<Item> listaMaior = this.verificaListaMaior(listaInicial, listaFinal);

		for (Item item : listaMenor) {
			if (!listaMaior.contains(item))
				throw new BadRequest(ITEM_NAO_ENCONTRADO);
		}

	}
	
	public final void verificaTraidor(Rebelde rebelde) {
		if(rebelde.isTraidor())
			throw new BadRequest(REBELDE_TRAIDOR);
	}
	
	private List<Item> verificaListaMenor(List<Item> listaInicial, List<Item> listaFinal) {
		if(listaInicial.size() > listaFinal.size())
			return listaFinal;
		return listaInicial;
	}
	
	private List<Item> verificaListaMaior(List<Item> listaInicial, List<Item> listaFinal) {
		if(listaInicial.size() > listaFinal.size())
			return listaInicial;
		return listaFinal;
	}
}
