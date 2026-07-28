package br.com.fiap.faseUm.FaseUm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.fiap.faseUm.FaseUm.entities.Endereco;
import br.com.fiap.faseUm.FaseUm.repositories.EnderecoRepository;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<Endereco> findAllEnderecos(int page, int size) {
        int offset = (page - 1) * size;
        return this.enderecoRepository.findAll(size, offset);
    }

    public Optional<Endereco> findEnderecoById(Long id) {
        return this.enderecoRepository.findById(id);
    }

    public void saveEndereco(Endereco endereco) {
        var save = this.enderecoRepository.save(endereco);
        Assert.state(save == 1, "Erro ao salvar Endereco " + endereco.getRua() + " " + endereco.getNumero() + " " + endereco.getBairro());
    }

    public void updateEndereco(Endereco endereco, Long id) {
        var update = this.enderecoRepository.update(endereco, id);
        if(update == 0 ) {
            throw new RuntimeException("Endereco não encontrado");
        }
    }

    public void delete(Long id) {
        var delete = this.enderecoRepository.delete(id);
        if(delete == 0 ) {
            throw new RuntimeException("Endereco não encontrado");
        }
    }
}
