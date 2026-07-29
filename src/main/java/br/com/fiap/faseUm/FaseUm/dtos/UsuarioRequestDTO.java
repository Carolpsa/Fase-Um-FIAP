package br.com.fiap.faseUm.FaseUm.dtos;

import br.com.fiap.faseUm.FaseUm.entities.TipoCadastro;

public record UsuarioRequestDTO(
    String nome, 
    
    String email, 
    
    String login, 
    
    String senha, 
    
    Long enderecoId, 
    
    TipoCadastro tipoCadastro) {

}
