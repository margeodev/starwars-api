package com.starwars.api.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.starwars.api.model.Rebelde;
import com.starwars.api.model.dto.RebeldeDTO;
import com.starwars.api.model.dto.RebeldeItemDTO;
import com.starwars.api.service.RebeldeService;

@RestController
@RequestMapping("/rebeldes")
public class RebeldeResource {

	@Autowired
	private RebeldeService service;
	

	@GetMapping
	public List<Rebelde> listar() {
		return service.listar();
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<Rebelde> buscarPorId(@PathVariable Long id) {
		Rebelde rebelde = service.procurarPorId(id);
		return ResponseEntity.ok(rebelde);
	}
	
		
	@PostMapping
	public ResponseEntity<Rebelde> adicionar(@RequestBody Rebelde rebelde) {
		Rebelde rebeldeSalvo = service.salvar(rebelde);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(rebeldeSalvo.getId()).toUri();

		return ResponseEntity.created(location).body(rebeldeSalvo);
	}
	
	
//	########################### PATCH ###########################
	@PatchMapping("/{id}")
	public ResponseEntity<Rebelde> atualizacaoParcial(
			@RequestBody RebeldeDTO rebeldeDTO, @PathVariable Long id) {
	     
		Rebelde rebeldeBanco = service.procurarPorId(id);
		
		if(rebeldeDTO.getLocalizacao() != null)
			rebeldeBanco.setLocalizacao(rebeldeDTO.getLocalizacao());
		
		if(rebeldeDTO.getDenuncia() != 0) {
			int denuncias = rebeldeBanco.getDenuncia();
			denuncias++;
			rebeldeBanco.setDenuncia(denuncias);
		}
		
	    service.salvar(rebeldeBanco);
	    return ResponseEntity.ok(rebeldeBanco);
	}

	
//	########################### PUT ###########################
	@PutMapping
	public ResponseEntity<Rebelde> trocaItens(
			@RequestBody RebeldeItemDTO[] rebeldeItemDTO) {

		Rebelde rebeldesAtualizado = service.trocarItens(rebeldeItemDTO[0], rebeldeItemDTO[1]);

	    return ResponseEntity.ok(rebeldesAtualizado);
	    
	}	

}
