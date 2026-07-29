package br.com.fiap.faseUm.FaseUm.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.faseUm.FaseUm.dtos.UsuarioRequestDTO;
import br.com.fiap.faseUm.FaseUm.dtos.UsuarioResponseDTO;
import br.com.fiap.faseUm.FaseUm.services.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

   @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> findAllUsuarios(
            @RequestParam("page") int page,
            @RequestParam("size") int size
   ) {
        logger.info("/usuarios");
        var usuarios = this.usuarioService.findAllUsuarios(page, size);
        return ResponseEntity.ok(usuarios);
   }


   @GetMapping("/{id}")
   public ResponseEntity<Optional<UsuarioResponseDTO>> findUsuario(
           @PathVariable("id") Long id
    ) {
        logger.info("/usuarios/"+ id);
        var usuario = this.usuarioService.findUsuarioById(id);
        return ResponseEntity.ok(usuario);
   }

   @PostMapping
   public ResponseEntity<Void> saveUsuario(
           @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO
   ) {
        logger.info("POST -> /usuarios");
        this.usuarioService.saveUsuario(usuarioRequestDTO);
        return ResponseEntity.status(201).build();
   }

   @PutMapping("/{id}")
   public ResponseEntity<Void> updateUsuario(
           @PathVariable("id") Long id,
           @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO
   ) {
       logger.info("PUT -> /usuarios/"+ id);
       this.usuarioService.updateUsuario(usuarioRequestDTO, id);
       var status = HttpStatus.NO_CONTENT;
       return ResponseEntity.status(status.value()).build();
   }

   @PatchMapping("/{id}/senha")
   public ResponseEntity<Void> updateSenhaUsuario(
           @PathVariable("id") Long id,
           @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO
   ) {
       logger.info("PATCH -> /usuarios/"+ id + "senha do usuario");
       this.usuarioService.updateSenhaUsuario(usuarioRequestDTO, id);
       var status = HttpStatus.NO_CONTENT;
       return ResponseEntity.status(status.value()).build();
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteUsuario(
           @PathVariable("id") Long id
   ) {
       logger.info("DELETE -> /usuarios/"+ id);
       this.usuarioService.delete(id);
       return ResponseEntity.ok().build();
   }

   @GetMapping("/usuarios/nome")
    public ResponseEntity<List<UsuarioResponseDTO>> findUsuarioByName(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("nome") String nome
   ) {
        logger.info("/usuarios/nome");
        var nomeUsuarios = this.usuarioService.findUsuarioByName(nome, page, size);
        return ResponseEntity.ok(nomeUsuarios);
   }


}
