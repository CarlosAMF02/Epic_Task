package br.com.fiap.epictaskapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.epictaskapi.model.Task;
import br.com.fiap.epictaskapi.model.User;
import br.com.fiap.epictaskapi.repository.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    TaskRepository repository;

    @Autowired
    UserService userService;
    
    public Page<Task> listAll(Pageable paginacao){
        return repository.findAll(paginacao);
    }

    public List<Task> listAll() {
        return repository.findAll();
    }

    public void save(Task task) {
        Task existTask = getById(task.getId()).orElse(null);

        if (existTask != null) {
            deleteById(existTask.getId());
        }
        
        repository.save(task);
    }

    public Optional<Task> getById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public int getUserScore(Long id) {
        User user = userService.getById(id).get();
        
        List<Task> userTasks = repository.findByUser(user);

        int score = 0;

        for (Task task : userTasks) {
            if(task.getStatus() == 100) {
                score += task.getScore();
            }
        }

        return score;
    }

}
