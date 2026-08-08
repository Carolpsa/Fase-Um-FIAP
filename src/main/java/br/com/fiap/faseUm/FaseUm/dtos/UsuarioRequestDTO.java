package br.com.fiap.faseUm.FaseUm.dtos;

import java.util.List;


import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDTO(
    @NotNull(message="O nome do usuario nao pode ser nulo.")
    String nome, 
    
    @NotNull(message="O email do usuario nao pode ser nulo.")
    String email, 
    
    @NotNull(message="O login do usuario nao pode ser nulo.")
    String login, 
    
    @NotNull(message="A senha do usuario nao pode ser nulo.")
    String senha, 
    
    @NotNull(message="O id endereco do usuario nao pode ser nulo.")
    Long enderecoId, 
    
    @NotNull(message="A role do usuario esta incorreta.")
    List<String> role 
)  {

}
