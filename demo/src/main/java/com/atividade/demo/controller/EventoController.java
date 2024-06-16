package com.atividade.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.atividade.demo.exception.InvalidEventoException;
import com.atividade.demo.service.EventoService;
import com.atividade.demo.model.Eventos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value="/api/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/add")
    public Eventos criarEvento(@RequestBody Eventos evento){
        return eventoService.addEvento(evento);
    }

    @GetMapping("/all")
    public List<Eventos> listarTodosEventos(){
        return eventoService.getAll();
    }

    @GetMapping("/{id}")
    public Eventos buscarEventoPorId(@PathVariable Long id){
        try {
            return eventoService.getById(id);
        } catch (InvalidEventoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/nome/{nome}")
    public List<Eventos> buscarEventoPorNome(@PathVariable String nome) {
        return eventoService.getByName(nome);
    }

    @PutMapping("/{id}")
    public Eventos atualizarEventos(@PathVariable Long id, @RequestBody Eventos eventoDetails) {
        try {
            return eventoService.updateEventoById(id, eventoDetails);
        } catch (InvalidEventoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletarEvento(@PathVariable Long id){
        try{
            eventoService.deleteById(id);
        } catch (InvalidEventoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
