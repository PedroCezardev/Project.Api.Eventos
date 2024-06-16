package com.atividade.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atividade.demo.exception.InvalidEventoException;
import com.atividade.demo.model.Eventos;
import com.atividade.demo.repository.EventoRepository;

@Service
public class EventoService {

    @Autowired 
    private EventoRepository eventoRepository;

    public List<Eventos> getAll() {
        return eventoRepository.findAll();
    }

    public Eventos addEvento(Eventos evento) {
        return eventoRepository.save(evento);
    }

    public Eventos getById(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new InvalidEventoException("O Evento não foi encontrado com id: " + id));
    }

    public List<Eventos> getByName(String nome) {
        return eventoRepository.findByNome(nome);
    }

    public void deleteById(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new InvalidEventoException("o Evento não foi encontrado com id: " + id);
        }
        eventoRepository.deleteById(id);
    }

    public Eventos updateEventoById(Long id, Eventos eventoDetails) {
        Optional<Eventos> optionalEvento = eventoRepository.findById(id);
        if (optionalEvento.isEmpty()) {
            throw new InvalidEventoException("Evento não encontrado com id: " + id);
        }

        validateEvento(eventoDetails);
        Eventos evento = optionalEvento.get();

        evento.setNome(eventoDetails.getNome());
        evento.setSubTitulo(eventoDetails.getSubTitulo());
        
        Eventos updatedEvento = eventoRepository.save(evento);
        return updatedEvento;
    }

    // algumas validacoes para atualizar um evento
    private void validateEvento(Eventos evento) {
        if (evento.getNome() == null || evento.getNome().isEmpty() || evento.getNome().length() > 100) {
            throw new InvalidEventoException("O nome do evento não pode ser nulo, vazio ou ter mais de 100 caracteres");
        }
        if (evento.getSubTitulo() == null || evento.getSubTitulo().isEmpty() || evento.getSubTitulo().length() > 50) {
            throw new InvalidEventoException("O SubTitulo do evento não pode ser nulo, vazio ou ter mais de 50 caracteres");
        }
    }
}