package br.com.fiap.faseUm.FaseUm.entities;

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
public class Restaurante {
    private Long id;
    private Usuario dono;
    private Endereco endereco;
    private Cardapio cardapio;
    private Comanda comanda;
}
