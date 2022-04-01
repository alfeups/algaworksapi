package com.algaworks.deliveryfood.domain.repository;

import java.util.List;

import com.algaworks.deliveryfood.domain.model.Cidade;

public interface CidadeRepository {

	List<Cidade> todasCidades();
	
	Cidade buscarCidadePorId(Long id);
	
	Cidade adicionarCidade(Cidade cidade);
	
	void removerCidade(Long id);
}
