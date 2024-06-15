package com.poo.spring.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.poo.aula.demo.DemoApplication;
import com.poo.aula.demo.model.Evento;
import com.poo.aula.demo.model.Usuario;
import com.poo.aula.demo.repository.EventoRepository;
import com.poo.aula.demo.repository.UsuarioRepository;

@SpringBootTest(classes = DemoApplication.class)
public class EventoTest {

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testEventoEntity() {
        Usuario criador = new Usuario();
        criador.setNome("Criador Teste");
        criador.setEmail("criador@teste.com");
        criador.setSenha("123456");
        criador.setIdade(30);
        usuarioRepository.save(criador);

        Evento evento = new Evento();
        evento.setNome("Evento Teste");
        evento.setSubtitulo("Subtitulo Teste");
        evento.setCriador(criador);

        evento = eventoRepository.save(evento);

        assertNotNull(evento.getId());
    }
}

