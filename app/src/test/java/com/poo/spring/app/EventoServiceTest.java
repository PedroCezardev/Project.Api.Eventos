package com.poo.spring.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.poo.aula.demo.DemoApplication;
import com.poo.aula.demo.exception.UserNotAllowedException;
import com.poo.aula.demo.model.Evento;
import com.poo.aula.demo.model.Usuario;
import com.poo.aula.demo.repository.EventoRepository;
import com.poo.aula.demo.repository.UsuarioRepository;
import com.poo.aula.demo.service.EventoService;

@SpringBootTest(classes = DemoApplication.class)
public class EventoServiceTest {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @BeforeEach
    public void setUp() {
        eventoRepository.deleteAll();
        usuarioRepository.deleteAll();
    }

    @Test
    public void testAddEvento() {
        Usuario usuario = new Usuario();
        usuario.setNome("Teste");
        usuario.setEmail("teste@teste.com");
        usuario.setSenha("123456");
        usuario.setIdade(25);
        usuarioRepository.save(usuario);

        Evento evento = new Evento();
        evento.setNome("Evento Teste");
        evento.setSubtitulo("Subtitulo Teste");

        Evento savedEvento = eventoService.addEvento(usuario.getId(), evento);
        assertNotNull(savedEvento);
        assertEquals("Evento Teste", savedEvento.getNome());
    }

    @Test
    public void testAddEventoUserNotAllowed() {
        Usuario usuario = new Usuario();
        usuario.setNome("Teste");
        usuario.setEmail("teste@teste.com");
        usuario.setSenha("123456");
        usuario.setIdade(17);
        usuarioRepository.save(usuario);

        Evento evento = new Evento();
        evento.setNome("Evento Teste");
        evento.setSubtitulo("Subtitulo Teste");

        assertThrows(UserNotAllowedException.class, () -> {
            eventoService.addEvento(usuario.getId(), evento);
        });
    }
}
