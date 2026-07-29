package br.com.fiap.faseUm.FaseUm.dtos;

import java.util.Date;

import br.com.fiap.faseUm.FaseUm.entities.TipoCadastro;

public record UsuarioResponseDTO(
    
    Long id,

    String nome,

    String email,

    String login,

    Date dataAlteracao,

    Long enderecoId,

    TipoCadastro tipoCadastro

) {
    
}
