package com.algaworks.deliveryfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.algaworks.deliveryfood.domain.model.Permissao;
import com.algaworks.deliveryfood.domain.repository.PermissaoRepository;

public class PermissaoRepositoryImpl implements PermissaoRepository{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Permissao> todas() {
		return manager.createQuery("from Permissao", Permissao.class)
	            .getResultList();
	}

	
	@Override
	public Permissao buscarPorId(Long id) {
		return manager.find(Permissao.class, id);
	}

	@Transactional
	@Override
	public Permissao adicionar(Permissao permissao) {
		return manager.merge(permissao);
	}

	@Transactional
	@Override
	public void remover(Permissao permissao) {
		permissao = buscarPorId(permissao.getId());
		manager.remove(permissao);
		
	}

}
