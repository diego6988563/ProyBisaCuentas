package com.demo.bancobisa.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
@Schema(description = "Balance de cuenta")
public class BalanceResponse implements Serializable {

    @Schema(description = "Balance de dinero", example = "100.38")
    private BigDecimal balance;

    @Schema(description = "Tipo de moneda", example = "Bs")
    private String currency;

    @Schema(description = "Fecha de ultima actualizacion", example = "Bs")
    private Date lastUpdated;
}
