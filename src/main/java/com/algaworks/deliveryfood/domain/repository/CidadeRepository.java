package com.algaworks.deliveryfood.domain.repository;

import java.util.List;

import com.algaworks.deliveryfood.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	List<Cidade> todasCidades();

	Cidade buscarCidadePorId(Long id);

	Cidade adicionarCidade(Cidade cidade);

	void removerCidade(Long id);
}
