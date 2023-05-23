package com.algaworks.deliveryfood.controller.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class Problem {


    private Integer status;
    private String type;
    private String title;
    private String detail;
}
