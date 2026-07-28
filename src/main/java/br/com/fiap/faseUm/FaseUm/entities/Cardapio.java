package br.com.fiap.faseUm.FaseUm.entities;

import java.util.List;
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
public class Cardapio {
    private List<Refeicao> refeicao;
    private List <Bebida> bebida;
}
