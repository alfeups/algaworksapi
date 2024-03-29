package com.algaworks.deliveryfood.controller.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    NAO_FOI_POSSIVEL_LER_BODY("/nao-foi-possivel-ler-body", "Não foi possível ler body da requisição."),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado."),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso."),
    NEGOCIO_EXPCETION("/negocio-exception", "Violação de regra de degócio."),
    PARAMETRO_INVALIDO("/parametro-invalido", "Paramêtro inválido."),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Ocorreu um erro interno."),
    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),;

    private String title;
    private String uri;

    ProblemType(String path, String title){
        this.uri = "https://dominio.com.br" + path;
        this.title = title;
    }
}
