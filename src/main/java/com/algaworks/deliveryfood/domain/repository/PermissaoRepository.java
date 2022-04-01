package com.algaworks.deliveryfood.domain.repository;

import java.util.List;


import com.algaworks.deliveryfood.domain.model.Permissao;

public interface PermissaoRepository {

	List<Permissao> todas();
	
	Permissao buscarPorId(Long id);
	
	Permissao adicionar(Permissao permissao);
	
	void remover(Permissao permissao);
}
