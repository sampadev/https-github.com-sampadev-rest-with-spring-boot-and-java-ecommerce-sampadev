package br.com.sampa.sampastore.repository;

import br.com.sampa.sampastore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{

    Optional<User> findByEmail(String email);
    Optional<User> findByCpf(String cpf);
    List<User> findByUsernameContainingIgnoreCase(String username);
}
