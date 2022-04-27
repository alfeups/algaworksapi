package com.algaworks.deliveryfood.controller;

import com.algaworks.deliveryfood.domain.exception.EntidadeEmUsoException;
import com.algaworks.deliveryfood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.deliveryfood.domain.model.Restaurante;
import com.algaworks.deliveryfood.domain.service.CadastroRestauranteUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

//@RestController
//@RequestMapping("/restaurantes")
public class RestauranteController {
	
//		@Autowired
//		private RestauranteRepository restauranteRepository;
//
//		@Autowired
//		private CadastroRestauranteUseCase cadastroRestauranteUseCase;
//
//		@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//		public List<Restaurante> listar(){
//			return restauranteRepository.listar();
//		}
//
//		@ResponseStatus(HttpStatus.OK)
//		@GetMapping(value = "/{restauranteId}", produces = MediaType.APPLICATION_JSON_VALUE)
//		public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
//			Restaurante restaurante = restauranteRepository.buscar(restauranteId);
//
//				if (restaurante != null) {
//				return ResponseEntity.ok(restaurante);
//					}
//
//			return ResponseEntity.notFound().build();
//		}
//
//		@PostMapping
//		@ResponseStatus(HttpStatus.CREATED)
//		public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
//			try{
//				restaurante = cadastroRestauranteUseCase.salvar(restaurante);
//				return ResponseEntity.status(HttpStatus.CREATED)
//						.body(restaurante);
//			} catch (EntidadeNaoEncontradaException e) {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//						.body(e.getMessage());
//			}
//
//		}
//
//
//		@PutMapping("/{restauranteId}")
//		public ResponseEntity<?> atualizar(@PathVariable Long restauranteId,
//				@RequestBody Restaurante restaurante) {
//			try {
//			Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);
//				if(restauranteAtual != null) {
//				BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
//				restauranteAtual = cadastroRestauranteUseCase.salvar(restauranteAtual);
//				return ResponseEntity.ok(restauranteAtual);
//			}
//				return ResponseEntity.notFound().build();
//				} catch (EntidadeNaoEncontradaException e) {
//				return ResponseEntity.badRequest()
//				.body(e.getMessage());
//				}
//			}
//
//		@DeleteMapping("/{restauranteId}")
//		public ResponseEntity<Restaurante> remover(@PathVariable Long restauranteId){
//			try {
//				cadastroRestauranteUseCase.excluir(restauranteId);
//				return ResponseEntity.noContent().build();
//
//			} catch (EntidadeNaoEncontradaException e) {
//				return ResponseEntity.notFound().build();
//
//			} catch (EntidadeEmUsoException e) {
//				return ResponseEntity.status(HttpStatus.CONFLICT).build();
//			}
//		}
//
//		@PatchMapping("/{restauranteId}")
//		public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
//												  @RequestBody Map<String, Object> campos){
//			Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);
//
//			if( restauranteAtual == null){
//				return ResponseEntity.notFound().build();
//			}
//			merge(campos, restauranteAtual);
//
//			return atualizar(restauranteId, restauranteAtual);
//
//		}
//
//	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
//		ObjectMapper objectMapper = new ObjectMapper();
//		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
//
//		System.out.println(restauranteOrigem);
//
//		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
//			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
//			field.setAccessible(true);
//
//			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
//
//			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);
//
//		ReflectionUtils.setField(field, restauranteDestino, novoValor);
//		});
//	}
}