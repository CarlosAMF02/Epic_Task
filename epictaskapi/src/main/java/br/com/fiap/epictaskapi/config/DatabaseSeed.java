package br.com.fiap.epictaskapi.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.epictaskapi.model.Role;
import br.com.fiap.epictaskapi.model.Task;
import br.com.fiap.epictaskapi.model.User;
import br.com.fiap.epictaskapi.repository.TaskRepository;
import br.com.fiap.epictaskapi.repository.UserRepository;

@Configuration
public class DatabaseSeed implements CommandLineRunner {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User(
            "Joao", 
            "joao@fiap.com.br", 
            "$2a$12$RVc1Cze5T/Ea6BclNRVwQejWSlaXOfBow8KC3MU0eifsyjfOFWKE2",
            new Role("ROLE_USER")
        );

        User user2 = new User(
            "Admin", 
            "admin@fiap.com.br", 
            "$2a$12$sSeIixKX2Ji4IglHLOQS8eQcFZc7n/O0OnfheuLA416YzBHf4A/Ge",
            new Role("ROLE_ADMIN")
        );

        userRepository.save(user1);

        userRepository.save(user2);

        taskRepository.saveAll(List.of(
            new Task("Modelar o BD", "Modelar as tabelas do banco", 100 , 20, user1), 
            new Task("Bug", "Modelar as tabelas do banco", 50 , 90, user2), 
            new Task("Prototipo", "Modelar as tabelas do banco", 30 , 70, user2), 
            new Task("Deploy", "Modelar as tabelas do banco", 10 , 25, user2), 
            new Task("Login", "Modelar as tabelas do banco", 40 , 12, user2), 
            new Task("Criar token", "Modelar as tabelas do banco", 55 , 10, user2), 
            new Task("Analise de dados", "Modelar as tabelas do banco", 5 , 60, user2), 
            new Task("Testes unitários", "Modelar as tabelas do banco", 100 , 40, user2), 
            new Task("Configurar ambiente", "Modelar as tabelas do banco", 100 , 0, user2), 
            new Task("Prototipo", "prototipar as telas", 100 , 70, user2)
        ));
        
    }
    
}
