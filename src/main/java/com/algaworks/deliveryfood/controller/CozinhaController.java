package com.algaworks.deliveryfood.controller;

import com.algaworks.deliveryfood.domain.exception.EntidadeEmUsoException;
import com.algaworks.deliveryfood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.deliveryfood.domain.model.Cozinha;
import com.algaworks.deliveryfood.domain.repository.CozinhaRepository;
import com.algaworks.deliveryfood.domain.service.CadastroCozinhaUseCase;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas") // (values = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {
	
		@Autowired
		private CozinhaRepository cozinhaRepository;
		
		@Autowired
		private CadastroCozinhaUseCase cadastroCozinha;
		
		@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) // (produces = {produces = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE} )
		public List<Cozinha> listar(){
			return cozinhaRepository.todas();
		}

		@ResponseStatus(HttpStatus.OK)
		@GetMapping(value = "/{cozinhaId}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Cozinha> buscarCozinhaPorId(@PathVariable Long cozinhaId) {
			Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
					
				if (cozinha != null) {
				return ResponseEntity.ok(cozinha);
					}
				
			return ResponseEntity.notFound().build();
		}

		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public Cozinha adicionar(@RequestBody Cozinha cozinha) {
			return cadastroCozinha.salvar(cozinha);
		}

		
		@PutMapping("/{cozinhaId}")
		public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId,
				@RequestBody Cozinha cozinha){
			Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);
			
				if(cozinhaAtual != null) {
				//cozinhaAtual.setNome(cozinha.getNome());
				
				BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
				
				cozinhaAtual = cadastroCozinha.salvar(cozinhaAtual);
				return ResponseEntity.ok(cozinhaAtual);
			
			}			
		
			return ResponseEntity.notFound().build();
		}
		
		@DeleteMapping("/{cozinhaId}")
		public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId){
			try {
				cadastroCozinha.excluir(cozinhaId);
				return ResponseEntity.noContent().build();
			
			} catch (EntidadeNaoEncontradaException e) {
				return ResponseEntity.notFound().build();
				
			} catch (EntidadeEmUsoException e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
		}

}


//HttpHeaders headers = new HttpHeaders();
//headers.add(HttpHeaders.LOCATION, "http://localhost:8080/cozinhas");
//
//return ResponseEntity
//		.status(HttpStatus.FOUND)
//		.headers(headers)
//		.build();
