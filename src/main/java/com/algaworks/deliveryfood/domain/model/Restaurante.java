package com.algaworks.deliveryfood.domain.model;

import com.algaworks.deliveryfood.core.validation.Groups;
import com.algaworks.deliveryfood.core.validation.ValorZeroIncluiDescricao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ValorZeroIncluiDescricao(
		valorField = "taxaFrete",
		descricaoField = "nome",
		descricaoObrigatoria= "Frete Grátis")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//@NotNull
	//@NotEmpty
	@NotBlank
	@Column(nullable = false)
	private String nome;


	//@DecimalMin("1")
	@NotNull
	//@Multiplo(numero = 5)
	@PositiveOrZero//(message = "{TaxaFrete.invalida}") // do bean validation, nao do spring. Por isso deve ser tratado no 'ValidationMessages.properties' e não no 'messages.properties' PS: Não manter 2 arquivos para tratar
	//@TaxaFrete //@PositiveOrZero message was overriding @TaxaFrete message // spring-framework issue open - 20519
	@Column(name = "taxa_frete", nullable = false)    // Resource bundle do spring sobrescreve se houver config, como o PositiveOrZero
	private BigDecimal taxaFrete;

	//@JsonIgnore
	//@JsonIgnoreProperties({"hibernateLazyInitializer"})
	@Valid //valida em cascata
	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
	@NotNull
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;

	@JsonIgnore
	@Embedded
	private Endereco endereco;

	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime dataCadastro;

	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime dataAtualizacao;

	//@JsonIgnore
	@ManyToMany//(fetch = FetchType.EAGER)
	@JoinTable(name = "restaurante_forma_pagamento",
			joinColumns = @JoinColumn(name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private List<FormaPagamento> formaPagamento = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<>();

}

