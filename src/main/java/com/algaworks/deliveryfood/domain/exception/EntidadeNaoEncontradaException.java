package com.algaworks.deliveryfood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntidadeNaoEncontradaException extends ResponseStatusException {
	
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(HttpStatus httpStatus, String mensagem) {
		super(httpStatus, mensagem);
	}

	public EntidadeNaoEncontradaException(String mensagem){
		this(HttpStatus.NOT_FOUND, mensagem);
	}
}