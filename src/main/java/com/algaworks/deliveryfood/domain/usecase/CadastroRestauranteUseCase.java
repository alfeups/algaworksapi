package com.algaworks.deliveryfood.domain.usecase;

import com.algaworks.deliveryfood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.deliveryfood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.deliveryfood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.deliveryfood.domain.model.Cozinha;
import com.algaworks.deliveryfood.domain.model.Restaurante;
import com.algaworks.deliveryfood.domain.repository.CozinhaRepository;
import com.algaworks.deliveryfood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
public class CadastroRestauranteUseCase {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
	}

	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();

		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));

		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEstadoNaoEncontradoException(EntidadeNaoEncontradaException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(e.getMessage());
	}

}
