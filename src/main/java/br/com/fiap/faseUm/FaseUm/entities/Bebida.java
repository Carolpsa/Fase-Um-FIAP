package br.com.fiap.faseUm.FaseUm.entities;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Bebida {
    private Long id;
    private String bebida;
    private BigDecimal valor;
    private int quantidade;

}
