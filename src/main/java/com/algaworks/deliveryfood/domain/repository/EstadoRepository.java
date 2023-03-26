package com.algaworks.deliveryfood.domain.repository;

import java.util.List;

import com.algaworks.deliveryfood.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

	}
