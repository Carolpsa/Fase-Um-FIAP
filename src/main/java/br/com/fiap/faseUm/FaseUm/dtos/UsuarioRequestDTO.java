package br.com.fiap.faseUm.FaseUm.dtos;

import br.com.fiap.faseUm.FaseUm.entities.TipoCadastro;
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
    
    @NotNull(message="O tipo de cadastro do usuario so pode ser DONO, DONA e CLIENTE.")
    TipoCadastro tipoCadastro) {

}
