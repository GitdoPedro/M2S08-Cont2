package br.com.senai.exemplospringaula4.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conta {

    @NotBlank
    private String numero;
    @NotBlank
    private String agencia;

    @NotBlank
    private double valor;


}
