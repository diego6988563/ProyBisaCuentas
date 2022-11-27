package com.demo.bancobisa.config;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Setter
@Getter
@Accessors(chain = true)
public class ErrorResponse implements Serializable {

    static final long serialVersionUID = 1L;

    private String code;
    private String message;

}