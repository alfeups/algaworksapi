package com.algaworks.deliveryfood.domain.repository;

import java.util.List;
import java.util.Optional;

import com.algaworks.deliveryfood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {

	List<Cozinha> findTodasByNomeContaining(String nome);
	Optional<Cozinha> findByNome(String nome);
	boolean existsByNome(String nome);

}
