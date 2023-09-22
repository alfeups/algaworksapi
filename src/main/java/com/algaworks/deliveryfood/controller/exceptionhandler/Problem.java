package com.algaworks.deliveryfood.controller.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class Problem {


    private Integer status;
    private LocalDateTime timestamp;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private List<Object> objects;


    @Getter
    @Builder
    public static class Object {
        private String name;
        private String userMessage;
    }

}
