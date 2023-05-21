package com.algaworks.deliveryfood.controller.exceptionhandler;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Problema {


    private LocalDateTime dataHora;
    private String mensagem;
}
