package com.demo.bancobisa.services.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
@Schema(description = "Detalle de transacciones realizadas")
public class Transaction {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String operation;
    private String accountNumber;
    private Date executeDate;

}
