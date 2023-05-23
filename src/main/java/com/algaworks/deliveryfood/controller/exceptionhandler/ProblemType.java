package com.algaworks.deliveryfood.controller.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada.");

    private String title;
    private String uri;

    ProblemType(String path, String title){
        this.uri = "https://dominio.com.br" + path;
        this.title = title;
    }
}
