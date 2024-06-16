package com.atividade.demo.repository;

import com.atividade.demo.model.Eventos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Eventos, Long> {

    List<Eventos> findByNome(String nome);
}
