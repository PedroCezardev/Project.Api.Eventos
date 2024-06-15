package com.poo.spring.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.poo.aula.demo.DemoApplication;
import com.poo.aula.demo.model.Usuario;
import com.poo.aula.demo.repository.UsuarioRepository;
import com.poo.aula.demo.service.UsuarioService;

@SpringBootTest(classes = DemoApplication.class)
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp() {
        usuarioRepository.deleteAll();
    }

    @Test
    public void testAddUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Teste");
        usuario.setEmail("teste@teste.com");
        usuario.setSenha("123456");
        usuario.setIdade(25);

        Usuario savedUsuario = usuarioService.addUsuario(usuario);
        assertNotNull(savedUsuario);
        assertEquals("Teste", savedUsuario.getNome());
    }

    @Test
    public void testLogin() {
        Usuario usuario = new Usuario();
        usuario.setNome("Teste");
        usuario.setEmail("teste@teste.com");
        usuario.setSenha("123456");
        usuario.setIdade(25);
        usuarioRepository.save(usuario);

        Optional<Usuario> loggedInUsuario = usuarioService.login("teste@teste.com", "123456");
        assertTrue(loggedInUsuario.isPresent());
        assertEquals("Teste", loggedInUsuario.get().getNome());
    }
}

