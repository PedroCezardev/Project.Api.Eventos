package com.atividade.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.atividade.demo.exception.InvalidUsuarioException;
import com.atividade.demo.service.UsuarioService;
import com.atividade.demo.model.Usuario;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value="/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/add")
    public Usuario criarUsuario(@RequestBody Usuario usuario){
        return usuarioService.addUsuario(usuario);
    }

    @GetMapping("/all")
    public List<Usuario> listarTodosUsuarios(){
        return usuarioService.getAll();
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuarioPorId(@PathVariable Long id){
        try {
            return usuarioService.getById(id);
        } catch (InvalidUsuarioException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/nome/{nome}")
    public List<Usuario> buscarUsuarioPorNome(@PathVariable String nome) {
        return usuarioService.getByName(nome);
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        try {
            return usuarioService.updateUsuarioById(id, usuarioDetails);
        } catch (InvalidUsuarioException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id){
        try{
            usuarioService.deleteById(id);
        } catch (InvalidUsuarioException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}