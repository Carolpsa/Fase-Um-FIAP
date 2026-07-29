package br.com.fiap.faseUm.FaseUm.controllers.handlers;

import java.util.List;

public record ValidationErrorDTO(List<String> errors, int status) {


} 


