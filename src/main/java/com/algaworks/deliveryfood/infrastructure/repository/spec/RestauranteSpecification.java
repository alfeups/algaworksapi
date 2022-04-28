package com.algaworks.deliveryfood.infrastructure.repository.spec;

import com.algaworks.deliveryfood.domain.model.Restaurante;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

@AllArgsConstructor
public class RestauranteSpecification {

    public static Specification<Restaurante> comFreteGratis(){
        return ((root, query, criteriaBuilder) -> //new RestauranteComFreteGratisSpecification();
                criteriaBuilder.equal(root.get("taxaFrete"), BigDecimal.ZERO));
    }

    public static Specification<Restaurante> comNomeSemelhante(String nome){
        return (root, query, criteriaBuilder) -> //new RestauranteComNomeSemelhanteSpecification(nome);
                criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
    }
}