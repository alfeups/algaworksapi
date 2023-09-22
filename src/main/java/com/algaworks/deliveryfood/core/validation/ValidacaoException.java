package com.algaworks.deliveryfood.core.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@AllArgsConstructor
@Getter
public class ValidacaoException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    private BindingResult bindingResult;
}
