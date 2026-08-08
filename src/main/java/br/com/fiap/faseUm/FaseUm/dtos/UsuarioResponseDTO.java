package br.com.fiap.faseUm.FaseUm.dtos;

import java.util.Date;
import java.util.List;


public record UsuarioResponseDTO(
    
    Long id,

    String nome,

    String email,

    String login,

    Date dataAlteracao,

    Long enderecoId,

    List<String> role 

) {
    
}
