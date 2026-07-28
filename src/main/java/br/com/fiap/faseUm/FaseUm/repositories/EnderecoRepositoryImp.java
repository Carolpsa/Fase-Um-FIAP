package br.com.fiap.faseUm.FaseUm.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import br.com.fiap.faseUm.FaseUm.entities.Endereco;

@Repository
public class EnderecoRepositoryImp implements EnderecoRepository{
    private final JdbcClient jdbcClient;
    
    public EnderecoRepositoryImp(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }
    
    
    @Override
    public Optional<Endereco> findById(Long id) {
        return this.jdbcClient.sql("SELECT * FROM enderecos WHERE id =:id").param("id", id).query(Endereco.class).optional();
    }

    @Override
    public List<Endereco> findAll(int size, int offset) {
        return this.jdbcClient.sql("SELECT * FROM enderecos LIMIT :size OFFSET :offset").param("size", size).param("offset", offset).query(Endereco.class).list();
    }

    @Override
    public Integer save(Endereco endereco) {
        return this.jdbcClient.sql("INSERTO INTO enderecos (rua, numero, bairro, cep, cidade) VALUES (:rua, :numero, :bairro, :cep, :cidade)")
        .param("rua", endereco.getRua())
        .param("numero", endereco.getNumero())
        .param("bairro", endereco.getBairro())
        .param("cep", endereco.getCep())
        .param("cidade", endereco.getCidade())
        .update();
    }

    @Override
    public Integer update(Endereco endereco, Long id) {
        return this.jdbcClient.sql("UPDATE enderecos SET rua = :rua, numero = :numero, bairro = :bairro, cep = :cep, cidade = :cidade WHERE id = :id")
        .param("id", id)
        .param("rua", endereco.getRua())
        .param("numero", endereco.getNumero())
        .param("bairro", endereco.getBairro())
        .param("cep", endereco.getCep())
        .param("cidade", endereco.getCidade())
        .update();
    }

    @Override
    public Integer delete(Long id) {
        return this.jdbcClient.sql("DELETE FROM enderecos WHERE id = :id")
        .param("id", id)
        .update();
    }
}
