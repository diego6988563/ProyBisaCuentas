package com.demo.bancobisa.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@Schema(description = "Consulta de balance de cuenta")
public class BalanceRequest implements Serializable {

    @NotNull
    @NotBlank
    @Schema(description = "Codigo de usuario", required = true, example = "TEST_1")
    private String userCode;

    @NotNull
    @NotBlank
    @Schema(description = "Numero de cuenta", required = true, example = "ABCDE")
    private String accountNumber;
}
