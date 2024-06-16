package com.atividade.demo.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name="usuario")
@Entity(name="usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 60, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String senha;
   
    @Column(nullable = false)
    private Integer idade;

    @OneToMany(mappedBy = "criador")
    private List<Eventos> eventosCriados;

    @ManyToMany(mappedBy = "usuariosInscritos")
    private List<Eventos> eventosInscritos; 
}
