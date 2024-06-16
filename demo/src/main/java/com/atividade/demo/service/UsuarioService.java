package com.atividade.demo.service;

import java.util.List;
import java.util.Optional;

import com.atividade.demo.exception.InvalidUsuarioException;
import com.atividade.demo.model.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.atividade.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired 
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario addUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario getById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new InvalidUsuarioException("O usuario não foi encontrado com id: " + id));
    }

    public List<Usuario> getByName(String nome) {
        return usuarioRepository.findByNome(nome);
    }

    public void deleteById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new InvalidUsuarioException("o Evento não foi encontrado com id: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    public Usuario login(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);
    
        if (usuario == null) {
            throw new InvalidUsuarioException("Usuário não encontrado com email: " + email);
        }

        return usuario;
    }

    public Usuario updateUsuarioById(Long id, Usuario usuarioDetails) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isEmpty()) {
            throw new InvalidUsuarioException("Usuario não encontrado com id: " + id);
        }

        validateUsuario(usuarioDetails);
        Usuario usuario = optionalUsuario.get();

        usuario.setNome(usuarioDetails.getNome());
        usuario.setEmail(usuarioDetails.getEmail());
        usuario.setSenha(usuarioDetails.getSenha());
        usuario.setIdade(usuarioDetails.getIdade());
        
        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return updatedUsuario;
    }

    // algumas validacoes para atualizar um usuario
    private void validateUsuario(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isEmpty() || usuario.getNome().length() > 100) {
            throw new InvalidUsuarioException("O nome do usuario não pode ser nulo, vazio ou ter mais de 100 caracteres");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty() || usuario.getEmail().length() > 60) {
            throw new InvalidUsuarioException("O Email do usuario não pode ser nulo, vazio ou ter mais de 60 caracteres");
        }
        if (usuario.getSenha() == null || usuario.getSenha().isEmpty() || usuario.getSenha().length() > 20) {
            throw new InvalidUsuarioException("A senha do usuario não pode ser nulo, vazio ou ter mais de 20 caracteres");
        }
        if (usuario.getIdade() <= 0) {
            throw new InvalidUsuarioException("A idade do usuario deve ser maior que zero");
        }
    }
}
