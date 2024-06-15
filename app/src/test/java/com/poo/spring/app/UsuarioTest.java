package com.poo.spring.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.poo.aula.demo.DemoApplication;
import com.poo.aula.demo.model.Usuario;
import com.poo.aula.demo.repository.UsuarioRepository;

@SpringBootTest(classes = DemoApplication.class)
public class UsuarioTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testUsuarioEntity() {
        Usuario usuario = new Usuario();
        usuario.setNome("Teste");
        usuario.setEmail("teste@teste.com");
        usuario.setSenha("123456");
        usuario.setIdade(25);

        Usuario savedUsuario = usuarioRepository.save(usuario);
        assertNotNull(savedUsuario);
        assertEquals("Teste", savedUsuario.getNome());
        assertEquals("teste@teste.com", savedUsuario.getEmail());
        assertEquals("123456", savedUsuario.getSenha());
        assertEquals(25, savedUsuario.getIdade());
    }
}