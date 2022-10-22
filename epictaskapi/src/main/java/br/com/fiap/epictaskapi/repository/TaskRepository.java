package br.com.fiap.epictaskapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.epictaskapi.model.Task;
import br.com.fiap.epictaskapi.model.User;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}
