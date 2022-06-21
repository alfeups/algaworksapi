package com.algaworks.deliveryfood.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class Endereco {

    @Column(name = "endereco_cep", nullable = false)
    private String cep;

    @Column(name = "endereco_logradouro", nullable = false)
    private String logradouro;

    @Column(name = "endereco_numero", nullable = false)
    private String numero;

    @Column(name = "endereco_complemento", nullable = true)
    private String complemento;

    @Column(name = "endereco_bairro", nullable = false)
    private String bairro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_cidade_id")
    private Cidade cidade;
}
