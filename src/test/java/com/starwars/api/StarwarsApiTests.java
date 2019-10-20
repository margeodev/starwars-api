package com.starwars.api;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.starwars.api.model.Localizacao;
import com.starwars.api.model.Rebelde;
import com.starwars.api.util.TestesUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StarwarsApiTests {

	@Autowired
	public WebApplicationContext context;
	
	private final String URI = "/rebeldes";
	
	private MockMvc mvc;
	
	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void test01ListarTodosOsRebeldes() throws Exception {
		this.mvc.perform(get(this.URI))
			.andExpect(status().isOk());
	}
	
	@Test
	public void test02AdicionarRebelde() throws Exception {		
		Rebelde rebelde = TestesUtils.novoRebelde();
		this.mvc.perform(post(this.URI)
			.content(TestesUtils.asJsonString(rebelde))
	        .contentType(MediaType.APPLICATION_JSON)
	        .accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isCreated())
	        .andExpect(header().string("Location", is("http://localhost" + this.URI + "/7")))
	        .andExpect(jsonPath("nome", equalTo(rebelde.getNome())))
	        .andExpect(jsonPath("idade", equalTo(rebelde.getIdade())))
	        .andExpect(jsonPath("genero", equalTo(rebelde.getGenero().toString())))
	        .andExpect(jsonPath("localizacao.latitude", equalTo(rebelde.getLocalizacao().getLatitude())))
	        .andExpect(jsonPath("localizacao.longitude", equalTo(rebelde.getLocalizacao().getLongitude())))
	        .andExpect(jsonPath("localizacao.nomeLocalizacao", equalTo(rebelde.getLocalizacao().getNomeLocalizacao())))
	        .andDo(MockMvcResultHandlers.print());
			
		}


	@Test
	public void test03DenunciarRebelde() throws Exception {
		this.mvc.perform(patch(this.URI + "/denuncia/1")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("denuncia", equalTo(1)))
			.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void test04AlteraLocalizacaoRebelde() throws Exception {
		Localizacao localizacao = TestesUtils.alteraLocalizacao();
		Rebelde rebelde = new Rebelde();
		rebelde.setLocalizacao(localizacao);
		this.mvc.perform(patch(this.URI + "/localizacao/1")
			.content(TestesUtils.asJsonString(rebelde))
		    .contentType(MediaType.APPLICATION_JSON)
		    .accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("localizacao.latitude", equalTo(localizacao.getLatitude())))
			.andExpect(jsonPath("localizacao.longitude", equalTo(localizacao.getLongitude())))
			.andExpect(jsonPath("localizacao.nomeLocalizacao", equalTo(localizacao.getNomeLocalizacao())))
			.andDo(MockMvcResultHandlers.print());
	}


	@Test
	public void test05TrocaItem() throws Exception {
		this.mvc.perform(put(this.URI)
			.content(TestesUtils.asJsonString(TestesUtils.oberListaItemDeRebeldesParaTrocar()))
		    .contentType(MediaType.APPLICATION_JSON)
		    .accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		
		this.mvc.perform(get(this.URI + "/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("inventario", hasSize(6)))
			.andExpect(jsonPath("inventario", containsInAnyOrder("ARMA", "MUNICAO", "COMIDA", "ARMA", "AGUA", "AGUA")))
			.andDo(MockMvcResultHandlers.print());
		
		this.mvc.perform(get(this.URI + "/3"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("inventario", hasSize(10)))
			.andExpect(jsonPath("inventario", containsInAnyOrder("AGUA", "ARMA", "MUNICAO", "MUNICAO", "MUNICAO", "MUNICAO", "MUNICAO", "MUNICAO", "COMIDA", "COMIDA")))
			.andDo(MockMvcResultHandlers.print());
		
	}

	
	@Test
	public void test06TransformaRebeldeEmTraidor() throws Exception {
		this.mvc.perform(patch(this.URI + "/denuncia/1")
			.contentType(MediaType.APPLICATION_JSON));
		
		this.mvc.perform(patch(this.URI + "/denuncia/1")
			.contentType(MediaType.APPLICATION_JSON));	
		
		this.mvc.perform(get(this.URI + "/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("traidor", equalTo(true)));
			
	}
	
	@Test
	public void test07TentaNegociarItemDeTraidor() throws Exception {
		this.mvc.perform(put(this.URI)
			.content(TestesUtils.asJsonString(TestesUtils.oberListaItemDeTraidoresParaTrocar()))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest());
						
	}	

	@Test
	public void test08ExcluiRebelde() throws Exception {
		this.mvc.perform(delete(this.URI + "/1"))
		.andExpect(status().isNoContent());		
	}	

	@Test
	public void test09TentaExcluiRebeldeFalha() throws Exception {
		this.mvc.perform(delete(this.URI + "/200"))
		.andExpect(status().isNotFound());		
	}	

	
}
