package com.algaworks.deliveryfood.domain.repository;

import java.util.List;

import com.algaworks.deliveryfood.domain.model.Cozinha;

public interface CozinhaRepository {
	
	
	List<Cozinha> todas();
	Cozinha buscar(Long id);
	Cozinha salvar(Cozinha cozinha);
	void remover(Long id);
}
