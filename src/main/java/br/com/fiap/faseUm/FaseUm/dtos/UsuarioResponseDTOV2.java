package br.com.fiap.faseUm.FaseUm.dtos;

import java.util.Date;

import br.com.fiap.faseUm.FaseUm.entities.Role;


public record UsuarioResponseDTOV2(
    
    Long id,

    String nomeCompleto,

    String email,

    String login,

    Date dataAlteracao,

    Long enderecoId,

    Role role,
    
    Boolean enable

) {
    
}
