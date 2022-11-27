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
@Schema(description = "Registro de cuenta")
public class RegisterAccountRequest implements Serializable {

    @NotNull
    @NotBlank
    @Schema(description = "Codigo de usuario", required = true, example = "PRUEBA_1")
    private String userCode;

    @NotNull
    @NotBlank
    @Schema(description = "Numero de cuenta", required = true, example = "4008974598")
    private String accountNumber;

    @NotNull
    @NotBlank
    @Schema(description = "Tipo de cuenta", required = true, example = "CUENTA AHORROS")
    private String accountType;

    @Min(0)
    @NotNull
    @Schema(description = "Saldo de dinero", required = true, example = "100")
    private Double balance;

    @NotBlank
    @NotNull
    @Schema(description = "Tipo de moneda", required = true, example = "Bs")
    private String currency;
}
