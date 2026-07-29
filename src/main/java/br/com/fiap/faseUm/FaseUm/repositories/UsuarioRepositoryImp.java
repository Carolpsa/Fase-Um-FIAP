package br.com.fiap.faseUm.FaseUm.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import br.com.fiap.faseUm.FaseUm.entities.Usuario;

@Repository
public class UsuarioRepositoryImp implements UsuarioRepository{

    private final JdbcClient jdbcClient;
    private final PasswordEncoder passwordEncoder;

    public UsuarioRepositoryImp(JdbcClient jdbcClient, PasswordEncoder passwordEncoder){
        this.jdbcClient = jdbcClient;
        this.passwordEncoder = passwordEncoder;
    }
    
    
    @Override
    public Optional<Usuario> findById(Long id) {
        return this.jdbcClient.sql("SELECT * FROM usuarios WHERE id =:id").param("id", id).query(Usuario.class).optional();
    }

    @Override
    public List<Usuario> findAll(int size, int offset) {
        return this.jdbcClient.sql("SELECT * FROM usuarios LIMIT :size OFFSET :offset").param("size", size).param("offset", offset).query(Usuario.class).list();
    }

    @Override
    public Integer save(Usuario usuario) {
        return this.jdbcClient.sql("INSERT INTO usuarios (nome, email, login, senha, data_alteracao, endereco_id, tipo_cadastro) VALUES (:nome, :email, :login, :senha, :data_alteracao, :endereco_id, :tipo_cadastro)")
        .param("nome", usuario.getNome())
        .param("email", usuario.getEmail())
        .param("login", usuario.getLogin())
        .param("senha", usuario.getSenha())
        .param("data_alteracao", LocalDate.now())
        .param("endereco_id", usuario.getEnderecoId())
        .param("tipo_cadastro", usuario.getTipoCadastro().name())
        .update();
    }

    @Override
    public Integer update(Usuario usuario, Long id) {
        return this.jdbcClient.sql("UPDATE usuarios SET nome = :nome, email = :email, login = :login, data_alteracao = :data_alteracao, endereco_id = :endereco_id, tipo_cadastro = :tipo_cadastro WHERE id = :id")
        .param("id", id)
        .param("nome", usuario.getNome())
        .param("email", usuario.getEmail())
        .param("login", usuario.getLogin())
        .param("data_alteracao", LocalDate.now())
        .param("endereco_id", usuario.getEnderecoId())
        .param("tipo_cadastro", usuario.getTipoCadastro().name())
        .update();
    }

    @Override
    public Integer updateSenhaUsuario(Usuario usuario, Long id) {
        return this.jdbcClient.sql("UPDATE usuarios SET senha = :senha, data_alteracao = :data_alteracao WHERE id = :id")
        .param("id", id)
        .param("senha", passwordEncoder.encode(usuario.getSenha()))
        .param("data_alteracao", LocalDate.now())
        .update();
    }

    @Override
    public Integer delete(Long id) {
        return this.jdbcClient.sql("DELETE FROM usuarios WHERE id = :id")
        .param("id", id)
        .update();
    }


    @Override
    public List<Usuario> findByName(String nome, int size, int offset) {
         return this.jdbcClient.sql("SELECT * FROM usuarios LIMIT :size OFFSET :offset nome :nome")
         .param("size", size)
         .param("offset", offset)
         .param("nome", nome)
         .query(Usuario.class)
         .list();
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
         return this.jdbcClient.sql("SELECT * FROM usuarios WHERE email = :email")
         .param("email", email)
         .query(Usuario.class)
         .optional();
    }

}
