package com.algaworks.deliveryfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.deliveryfood.domain.model.Cidade;
import com.algaworks.deliveryfood.domain.model.Cozinha;
import com.algaworks.deliveryfood.domain.model.Permissao;
import com.algaworks.deliveryfood.domain.repository.CidadeRepository;
import com.algaworks.deliveryfood.domain.repository.CozinhaRepository;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Cidade> todasCidades() {
		return manager.createQuery("from Cidade", Cidade.class)
				.getResultList();
	}

	@Override
	public Cidade buscarCidadePorId(Long id) {
		return manager.find(Cidade.class, id);
	}

	@Transactional
	@Override
	public Cidade adicionarCidade(Cidade cidade) {
		return manager.merge(cidade);
	}
	
	@Transactional
	@Override
	public void removerCidade(Long id) {
		Cidade cidade = buscarCidadePorId(id);

		if (cidade == null) {
			throw new EmptyResultDataAccessException(1);
		}

		manager.remove(cidade);
	}

}
