package com.starwars.api.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.starwars.api.model.Relatorio;
import com.starwars.api.model.dto.RebeldeItemDTO;
import com.starwars.api.model.dto.RebeldeLocalizacaoDTO;
import com.starwars.api.service.RebeldeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rebeldes")
@Api(value = "API rebeldes")
@CrossOrigin(origins="*")
public class RebeldeResource {

	@Autowired
	private RebeldeService service;
	
//	########################### GET ###########################
	@GetMapping
	@ApiOperation(value = "Retorna lista com os rebeldes que não são traidores")
	public List<Rebelde> listarRebeldes() {
		return service.listarRebeldes();
	}

	@GetMapping("/traidores")
	@ApiOperation(value = "Retorna lista com todos rebeldes traidores")
	public List<Rebelde> listarTraidores() {
		return service.listarTraidores();
	}

	@GetMapping("/dashboard")
	@ApiOperation(value = "Gera relatório com percentual de rebeldes, traidores, somatório dos pontos perdidos dos traidores e média de itens por rebeldes")
	public ResponseEntity<Relatorio> gerarRelatorios() {
		
		Relatorio relatorios =  service.geraRelatorios();
		
		return ResponseEntity.ok(relatorios);
	}

	
	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um rebelde pelo id")
	public ResponseEntity<Rebelde> buscarPorId(@PathVariable Long id) {
		Rebelde rebelde = service.procurarPorId(id);
		return ResponseEntity.ok(rebelde);
	}
	
	
//	########################### POST ###########################
	@PostMapping
	@ApiOperation(value = "Adiciona um novo rebelde")	
	public ResponseEntity<Rebelde> adicionar(@RequestBody Rebelde rebelde) {
		Rebelde rebeldeSalvo = service.salvar(rebelde);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(rebeldeSalvo.getId()).toUri();

		return ResponseEntity.created(location).body(rebeldeSalvo);
	}
	
	
//	########################### PATCH ###########################
	@ApiOperation(value = "Altera a localização do rebelde")
	@PatchMapping("/localizacao/{id}")
	public ResponseEntity<Rebelde> alteraLocalizacao(
			@RequestBody RebeldeLocalizacaoDTO rebeldeDTO, @PathVariable Long id) {
	     
		Rebelde rebeldeBanco = service.procurarPorId(id);
		
		rebeldeBanco.setLocalizacao(rebeldeDTO.getLocalizacao());
		
	    service.salvar(rebeldeBanco);
	    return ResponseEntity.ok(rebeldeBanco);
	}

	@PatchMapping("/denuncia/{id}")
	@ApiOperation(value = "Denuncia o rebelde como traidor")
	public ResponseEntity<Rebelde> denuncia(@PathVariable Long id) {
		
		Rebelde rebeldeBanco = service.procurarPorId(id);
				
		int denuncias = rebeldeBanco.getDenuncia();
		denuncias++;
		rebeldeBanco.setDenuncia(denuncias);
		
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

	
//	########################### DELETE ###########################
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Exclui rebelde pelo id")
	public ResponseEntity<Rebelde> excluir(@PathVariable Long id) {
		service.excluir(id);
		return ResponseEntity.noContent().build();
	}

}
