package br.com.fiap.faseUm.FaseUm.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.faseUm.FaseUm.entities.Endereco;
import br.com.fiap.faseUm.FaseUm.services.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    private static final Logger logger = LoggerFactory.getLogger(EnderecoController.class);

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

   @GetMapping
    public ResponseEntity<List<Endereco>> findAllEnderecos(
            @RequestParam("page") int page,
            @RequestParam("size") int size
   ) {
        logger.info("/enderecos");
        var enderecos = this.enderecoService.findAllEnderecos(page, size);
        return ResponseEntity.ok(enderecos);
   }


   @GetMapping("/{id}")
   public ResponseEntity<Optional<Endereco>> findEndereco(
           @PathVariable("id") Long id
    ) {
        logger.info("/enderecos/"+ id);
        var endereco = this.enderecoService.findEnderecoById(id);
        return ResponseEntity.ok(endereco);
   }

   @PostMapping
   public ResponseEntity<Void> saveEndereco(
           @RequestBody Endereco endereco
   ) {
        logger.info("POST -> /usuarios");
        this.enderecoService.saveEndereco(endereco);
        return ResponseEntity.status(201).build();
   }

   @PutMapping("/{id}")
   public ResponseEntity<Void> updateEndereco(
           @PathVariable("id") Long id,
           @RequestBody Endereco endereco
   ) {
       logger.info("PUT -> /enderecos/"+ id);
       this.enderecoService.updateEndereco(endereco, id);
       var status = HttpStatus.NO_CONTENT;
       return ResponseEntity.status(status.value()).build();
   }


   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteEndereco(
           @PathVariable("id") Long id
   ) {
       logger.info("DELETE -> /enderecos/"+ id);
       this.enderecoService.delete(id);
       return ResponseEntity.ok().build();
   }
}
