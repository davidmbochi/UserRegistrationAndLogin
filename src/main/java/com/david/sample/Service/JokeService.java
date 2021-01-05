package com.david.sample.Service;

import com.david.sample.model.Joke;

import java.util.List;

public interface JokeService {
    List<Joke> getAllJokes();
    void save(Joke joke);
    Joke getJoke(Long id);
    void delete(Long id);
}
