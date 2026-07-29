package br.com.fiap.faseUm.FaseUm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.fiap.faseUm.FaseUm.dtos.UsuarioRequestDTO;
import br.com.fiap.faseUm.FaseUm.dtos.UsuarioResponseDTO;
import br.com.fiap.faseUm.FaseUm.entities.Usuario;
import br.com.fiap.faseUm.FaseUm.repositories.UsuarioRepository;
import br.com.fiap.faseUm.FaseUm.services.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponseDTO> findAllUsuarios(int page, int size) {
        int offset = (page - 1) * size;
        List<Usuario> usuarios = this.usuarioRepository.findAll(size, offset);
        return usuarios.stream().map(this::response).toList();

    }
    
    public Optional<UsuarioResponseDTO> findUsuarioById(Long id) {
        return Optional.ofNullable(this.usuarioRepository.findById(id).map(this::response).orElseThrow(()-> new ResourceNotFoundException("Usuario nao encontrado")));
        
    }

    public void saveUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        var optional = this.usuarioRepository.findByEmail(usuarioRequestDTO.email());
        Assert.state(optional.isEmpty(), "Este e-mail ja esta cadastrado: " + usuarioRequestDTO.email());
        var usuario = this.usuarioRequest(usuarioRequestDTO);
        var save = this.usuarioRepository.save(usuario);
        Assert.state(save == 1, "Erro ao salvar usuario " + usuarioRequestDTO.nome());

    }

    public void updateUsuario(UsuarioRequestDTO usuarioRequestDTO, Long id) {
        var optional = this.usuarioRepository.findByEmail(usuarioRequestDTO.email());
        Assert.state(optional.isEmpty() || optional.get().getId().equals(id), "Este e-mail ja esta cadastrado: " + usuarioRequestDTO.email());
        var usuario = this.usuarioRequest(usuarioRequestDTO);
        var update = this.usuarioRepository.update(usuario, id);
        Assert.state(update == 0, "Usuario não encontrado");
    }

    public void updateSenhaUsuario(UsuarioRequestDTO usuarioRequestDTO, Long id) {
        var usuario = this.usuarioRequest(usuarioRequestDTO);
        var update = this.usuarioRepository.updateSenhaUsuario(usuario, id);
        Assert.state(update == 0, "Usuario não encontrado");
        
    }

    public void delete(Long id) {
        var delete = this.usuarioRepository.delete(id);
        Assert.state(delete == 0, "Usuario não encontrado");
        
    }

    public List<UsuarioResponseDTO> findUsuarioByName(String nome, int page, int size) {
        int offset = (page - 1) * size;
        return this.usuarioRepository.findByName(nome, size, offset).stream().map(this::response).toList();
    }

    private UsuarioResponseDTO response(Usuario usuario){
        return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getLogin(),
            usuario.getDataAlteracao(),
            usuario.getEnderecoId(),
            usuario.getTipoCadastro()
            
        );
    }

    private Usuario usuarioRequest (UsuarioRequestDTO usuarioRequestDTO){
        var usuarioRequest = new Usuario();
            usuarioRequest.setNome(usuarioRequestDTO.nome());
            usuarioRequest.setEmail(usuarioRequestDTO.email());
            usuarioRequest.setLogin(usuarioRequestDTO.login());
            usuarioRequest.setEnderecoId(usuarioRequestDTO.enderecoId());
            usuarioRequest.setTipoCadastro(usuarioRequestDTO.tipoCadastro());
            return usuarioRequest;
            
    }


}
