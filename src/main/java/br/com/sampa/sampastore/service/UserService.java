package br.com.sampa.sampastore.service;

import br.com.sampa.sampastore.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    void registeruser(User user);
    Optional<User> findByEmail(String email);
    List<User> listarUsuarios();
    User buscarPorId(Long userId);
    void atualizarUsuario(Long userId, User usuarioAtualizado);
}
