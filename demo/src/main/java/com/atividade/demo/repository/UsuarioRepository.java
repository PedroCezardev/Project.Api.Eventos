package com.atividade.demo.repository;

import com.atividade.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByNome(String nome);
    Usuario findByEmail(String email);
    Optional<Usuario> findBySenha(String senha);
    
}
