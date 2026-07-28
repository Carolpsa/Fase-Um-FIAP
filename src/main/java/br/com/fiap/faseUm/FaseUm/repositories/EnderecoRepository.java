package br.com.fiap.faseUm.FaseUm.repositories;

import java.util.List;
import java.util.Optional;

import br.com.fiap.faseUm.FaseUm.entities.Endereco;

public interface EnderecoRepository {
    Optional<Endereco> findById(Long id);

    List<Endereco> findAll(int size, int offset);

    Integer save(Endereco endereco); // integer porque retorna a quantidade de linhas modificadas

    Integer update (Endereco endereco, Long id); // integer porque retorna a quantidade de linhas modificadas

    Integer delete(Long id); // integer porque retorna a quantidade de linhas modificadas
}
