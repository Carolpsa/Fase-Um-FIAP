package br.com.fiap.faseUm.FaseUm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.fiap.faseUm.FaseUm.entities.Usuario;
import br.com.fiap.faseUm.FaseUm.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAllUsuarios(int page, int size) {
        int offset = (page - 1) * size;
        return this.usuarioRepository.findAll(size, offset);
    }

    public Optional<Usuario> findUsuarioById(Long id) {
        return this.usuarioRepository.findById(id);
    }

    public void saveUsuario(Usuario usuario) {
        var optional = this.usuarioRepository.findByEmail(usuario.getEmail());
        Assert.state(optional.isEmpty(), "Este e-mail ja esta cadastrado: " + usuario.getEmail());
        var save = this.usuarioRepository.save(usuario);
        Assert.state(save == 1, "Erro ao salvar usuario " + usuario.getNome());

    }


    public void updateUsuario(Usuario usuario, Long id) {
        var optional = this.usuarioRepository.findByEmail(usuario.getEmail());
        Assert.state(optional.isEmpty() || optional.get().getId().equals(id), "Este e-mail ja esta cadastrado: " + usuario.getEmail());
        var update = this.usuarioRepository.update(usuario, id);
        if(update == 0 ) {
            throw new RuntimeException("Usuario não encontrado");
        }
    }

    public void updateSenhaUsuario(Usuario usuario, Long id) {
        var update = this.usuarioRepository.updateSenhaUsuario(usuario, id);
        if(update == 0 ) {
            throw new RuntimeException("Usuario não encontrado");
        }
    }

    public void delete(Long id) {
        var delete = this.usuarioRepository.delete(id);
        if(delete == 0 ) {
            throw new RuntimeException("Usuario não encontrado");
        }
    }

    public List<Usuario> findUsuarioByName(String nome, int page, int size) {
        int offset = (page - 1) * size;
        return this.usuarioRepository.findByName(nome, size, offset);
    }

}
