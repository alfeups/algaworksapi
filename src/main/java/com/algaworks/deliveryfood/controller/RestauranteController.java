package com.algaworks.deliveryfood.controller;

import com.algaworks.deliveryfood.core.validation.ValidacaoException;
import com.algaworks.deliveryfood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.deliveryfood.domain.exception.NegocioException;
import com.algaworks.deliveryfood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.deliveryfood.domain.model.Restaurante;
import com.algaworks.deliveryfood.domain.repository.RestauranteRepository;
import com.algaworks.deliveryfood.domain.usecase.CadastroRestauranteUseCase;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteUseCase cadastroRestaurante;

	@Autowired
	private SmartValidator smartValidator;

	@GetMapping
	public List<Restaurante> listar() {
		List<Restaurante> restaurantes = restauranteRepository.findAll();

		restaurantes.get(0).getCozinha().getNome();

		return restaurantes;
	}

	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante =
				restauranteRepository.findById(restauranteId)
						.orElseThrow(() ->
								new RestauranteNaoEncontradoException(restauranteId));

		if (restaurante != null) {
			return ResponseEntity.ok(restaurante);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody @Valid Restaurante restaurante) {
		try {
			return cadastroRestaurante.salvar(restaurante);
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{restauranteId}")
	public Restaurante atualizar(@PathVariable Long restauranteId,
								 @RequestBody @Valid Restaurante restaurante) {
		try {
			Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

			BeanUtils.copyProperties(restaurante, restauranteAtual,
					"id", "formasPagamento", "endereco", "dataCadastro", "produtos");

			return cadastroRestaurante.salvar(restauranteAtual);
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PatchMapping("/{restauranteId}")
	public Restaurante atualizarParcial(@PathVariable Long restauranteId,
										@RequestBody Map<String, Object> campos,
										HttpServletRequest request) {
		var restauranteAtual = restauranteRepository
				.findById(restauranteId)
				.orElseThrow(() ->
						new RestauranteNaoEncontradoException(restauranteId));

		merge(campos, restauranteAtual, request);
		validate(restauranteAtual, "restaurante");

		return atualizar(restauranteId, restauranteAtual);
	}

	private void validate(Restaurante restaurante, String objectName) {
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
		smartValidator.validate(restaurante, bindingResult);

		if (bindingResult.hasErrors()){
			throw new ValidacaoException(bindingResult);
		}
	}

	private void merge(Map<String, Object> dadosOrigem,
					   Restaurante restauranteDestino,
					   HttpServletRequest request) {
		ServletServerHttpRequest servletServerHttpRequest = new ServletServerHttpRequest(request);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

			Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

			dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
				Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
				assert field != null;
				field.setAccessible(true);

				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
				ReflectionUtils.setField(field, restauranteDestino, novoValor);
			});
		}catch (IllegalArgumentException e){
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, servletServerHttpRequest);
		}
	}

}