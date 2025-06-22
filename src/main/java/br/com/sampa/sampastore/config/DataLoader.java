package br.com.sampa.sampastore.config;

import br.com.sampa.sampastore.entity.User;
import br.com.sampa.sampastore.enums.Grupo;
import br.com.sampa.sampastore.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;

    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Criar Usuário Admin Padrão
        if (userService.findByEmail("admin@sampastore.com").isEmpty()) {
            User admin = new User();
            admin.setUsername("Admin");
            admin.setEmail("admin@sampastore.com");
            admin.setCpf("60852461020"); // Um CPF válido é exigido pelo CpfValidator
            admin.setPassword("admin123"); // A senha será codificada pelo UserService
            admin.setConfPassword("admin123");
            admin.setGrupo(Grupo.ADMIN);
            admin.setStatus(true);
            try {
                userService.registeruser(admin);
                System.out.println("Usuário Admin padrão criado!");
            } catch (IllegalArgumentException e) {
                System.err.println("Erro ao criar Admin padrão: " + e.getMessage());
            }
        } else {
            System.out.println("Usuário Admin padrão já existe.");
        }

        // Criar Usuário Estoquista Padrão
        if (userService.findByEmail("estoquista@sampastore.com").isEmpty()) {
            User estoquista = new User();
            estoquista.setUsername("Estoquista");
            estoquista.setEmail("estoquista@sampastore.com");
            estoquista.setCpf("18552843080"); // Um CPF válido é exigido pelo CpfValidator
            estoquista.setPassword("estoque123"); // A senha será codificada pelo UserService
            estoquista.setConfPassword("estoque123");
            estoquista.setGrupo(Grupo.ESTOQUISTA);
            estoquista.setStatus(true);
            try {
                userService.registeruser(estoquista);
                System.out.println("Usuário Estoquista padrão criado!");
            } catch (IllegalArgumentException e) {
                System.err.println("Erro ao criar Estoquista padrão: " + e.getMessage());
            }
        } else {
            System.out.println("Usuário Estoquista padrão já existe.");
        }
    }
}