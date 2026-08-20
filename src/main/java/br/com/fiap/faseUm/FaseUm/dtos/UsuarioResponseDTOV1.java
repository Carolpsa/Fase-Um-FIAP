package br.com.fiap.faseUm.FaseUm.dtos;

import java.util.Date;

import br.com.fiap.faseUm.FaseUm.entities.Role;


public record UsuarioResponseDTOV1(
    
    Long id,

    String nome,

    String email,

    String login,

    Date dataAlteracao,

    Long enderecoId,

    Role role,
    
    Boolean enable

) {
    
}
