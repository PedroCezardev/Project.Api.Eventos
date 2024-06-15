package com.poo.spring.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.poo.aula.demo.DemoApplication;
import com.poo.aula.demo.model.Usuario;
import com.poo.aula.demo.repository.UsuarioRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

@SpringBootTest(classes = DemoApplication.class)
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testFindByEmailAndSenha() {
        Usuario usuario = new Usuario();
        usuario.setNome("Teste");
        usuario.setEmail("teste@teste.com");
        usuario.setSenha("123456");
        usuario.setIdade(25);
        usuarioRepository.save(usuario);

        Optional<Usuario> foundUsuario = usuarioRepository.findByEmailAndSenha("teste@teste.com", "123456");
        assertTrue(foundUsuario.isPresent());
        assertEquals("Teste", foundUsuario.get().getNome());
    }
}

