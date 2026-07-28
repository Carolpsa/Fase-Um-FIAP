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
public class Comanda {
    private Long id;
    private Refeicao refeicao;
    private Bebida bebida;
    private BigDecimal valorTotal;
    private Usuario cliente;
}
