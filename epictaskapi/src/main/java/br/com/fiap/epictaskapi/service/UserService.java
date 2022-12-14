package br.com.fiap.epictaskapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fiap.epictaskapi.model.User;
import br.com.fiap.epictaskapi.repository.UserRepository;
import br.com.fiap.epictaskapi.service.Models.RankedUser;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
    public Page<User> listAll(Pageable paginacao){
        return userRepository.findAll(paginacao);
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public List<RankedUser> ranking() {
        List<User> users =  userRepository.findByOrderByScoreDesc();
        ArrayList<RankedUser> ranking = new ArrayList<RankedUser>();
        for (int i = 0; i < users.size(); i++) {
            RankedUser ru = new RankedUser();
            int rank = i + 1;
            ru.setRank(rank);
            ru.setScore(users.get(i).getScore());
            ru.setEmail(users.get(i).getEmail());
            ru.setNome(users.get(i).getName());
            ranking.add(ru);
        }
        return ranking;
    }

    public void save(User user) {
        String password = new BCryptPasswordEncoder().encode(user.getPassword());

        user.setPassword(password);

        userRepository.save(user);
    }

    public void saveScore(User user) {
        User existUser = getById(user.getId()).orElse(null);
        if ( existUser != null) {
            existUser.setScore(user.getScore());
            userRepository.save(existUser);
        }
    }

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
