package com.algaworks.deliveryfood.controller;

import com.algaworks.deliveryfood.domain.model.Cozinha;
import com.algaworks.deliveryfood.domain.model.Restaurante;
import com.algaworks.deliveryfood.domain.repository.CozinhaRepository;
import com.algaworks.deliveryfood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.algaworks.deliveryfood.infrastructure.repository.spec.RestauranteSpecification.comFreteGratis;
import static com.algaworks.deliveryfood.infrastructure.repository.spec.RestauranteSpecification.comNomeSemelhante;

@RestController
@RequestMapping("/teste") // (values = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class TesteCozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private RestauranteRepository restauranteRepository;

	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhasPorNome(String nome){
		return cozinhaRepository.findTodasByNomeContaining(nome);
	}

	@GetMapping("/cozinhas/unica-por-nome")
	public Optional<Cozinha> cozinhaPorNome(String nome){
		return cozinhaRepository.findByNome(nome);
	}

	@GetMapping("/cozinhas/exists")
	public boolean cozinhaExists(String nome){
		return cozinhaRepository.existsByNome(nome);
	}

	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal){
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}

	@GetMapping("/restaurantes/por-nome")
	public List<Restaurante> restaurantePorNome(String nome, Long cozinha){
		return restauranteRepository.consultarPorNome(nome, cozinha);
	}

	@GetMapping("/restaurantes/primeiro-por-nome")
	public Optional<Restaurante> restaurantePorNomeContendo(String nome){
		return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
	}

	@GetMapping("/restaurantes/top2-por-nome")
	public List<Restaurante> restaurantesTopDoisPorNome(String nome){
		return restauranteRepository.findTop2ByNomeContaining(nome);
	}

	@GetMapping("/restaurantes/findtop3bynomecontaining")
	public List<Restaurante> restaurantesTopTresPorNome(String nome){
		return restauranteRepository.findTop3ByNomeContaining(nome);
	}

	@GetMapping("/restaurantes/exists-por-nome")
	public Boolean restaurantesExists(String nome){
		return restauranteRepository.existsByNome(nome);
	}

	@GetMapping("/restaurantes/count-por-cozinha")
	public int contaPorCozinha(Long cozinhaId){
		return restauranteRepository.countByCozinhaId(cozinhaId);
	}

	@GetMapping("/restaurantes/por-nome-e-frete")
	public List<Restaurante> buscaPorNomeETaxaFrete(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}
	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> buscarRestaurantesComFreteGratis(String nome){

		return restauranteRepository.findAll(comFreteGratis()
				.and(comNomeSemelhante(nome)));
	}

}