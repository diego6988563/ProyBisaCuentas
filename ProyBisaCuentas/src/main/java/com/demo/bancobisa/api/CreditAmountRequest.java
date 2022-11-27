package com.demo.bancobisa.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
@Schema(description = "Datos de la respuesta de seguridad")
public class CreditAmountRequest implements Serializable {

    @NotNull
    @NotBlank
    @Schema(description = "Codigo de usuario", required = true, example = "PRUEBA_1")
    private String userCode;

    @NotNull
    @NotBlank
    @Schema(description = "Numero de cuenta", required = true, example = "4008974598")
    private String accountNumber;

    @Min(0)
    @NotNull
    @Schema(description = "Saldo de dinero", required = true, example = "10")
    private Double amount;

    @Min(0)
    @NotNull
    @Schema(description = "Tipo de moneda", required = true, example = "0")
    private Double currency;

}
