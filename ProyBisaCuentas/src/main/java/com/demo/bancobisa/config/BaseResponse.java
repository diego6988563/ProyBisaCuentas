package com.demo.bancobisa.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@ToString
@Accessors(chain = true)
public class BaseResponse<T extends Serializable> implements Serializable {

    public static final long serialVersionUID = 1L;

    private String transactionId;
    private T result;
    private Date timestamp;

    public BaseResponse() {
        this.transactionId = UUID.randomUUID().toString();
        this.timestamp = new Date();
    }

}

