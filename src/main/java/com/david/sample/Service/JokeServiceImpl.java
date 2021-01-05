package com.david.sample.Service;

import com.david.sample.model.Joke;
import com.david.sample.model.User;
import com.david.sample.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class JokeServiceImpl implements JokeService{
    private final JokeRepository jokeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    public JokeServiceImpl(JokeRepository jokeRepository){
        this.jokeRepository = jokeRepository;
    }

    @Override
    @Transactional
    public List<Joke> getAllJokes() {
        return jokeRepository.getAllJokes();
    }

    @Override
    @Transactional
    public void save(Joke theJoke) {
        jokeRepository.save(theJoke);
    }

    @Override
    @Transactional
    public Joke getJoke(Long id) {
        return jokeRepository.getJoke(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        jokeRepository.delete(id);
    }
}
