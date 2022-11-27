package com.demo.bancobisa.services.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
@Schema(description = "Datos de la respuesta de seguridad")
public class Account implements Serializable {

    private Long id;
    private String userCode;
    private String accountNumber;
    private String accountType;
    private Double balance;
    private Double currency;
    private String state;
    private Date lastUpdate;
}
