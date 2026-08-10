package br.com.fiap.faseUm.FaseUm.repositories;

import java.util.List;
import java.util.Optional;

import br.com.fiap.faseUm.FaseUm.entities.Usuario;

public interface UsuarioRepository {
    
    Optional<Usuario> findById(Long id);

    List<Usuario> findAll(int size, int offset);

    List<Usuario> findByNameList(String name, int size, int offset);

    Optional<Usuario> findByName(String name);

    Integer save(Usuario usuario); // integer porque retorna a quantidade de linhas modificadas

    Integer update (Usuario usuario, Long id); // integer porque retorna a quantidade de linhas modificadas

    Integer updateSenhaUsuario (Usuario usuario, Long id); // integer porque retorna a quantidade de linhas modificadas

    Integer delete(Long id); // integer porque retorna a quantidade de linhas modificadas

    Optional<Usuario> findByEmail(String email);

}

