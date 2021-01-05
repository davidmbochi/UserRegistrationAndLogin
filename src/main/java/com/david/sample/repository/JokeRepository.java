package com.david.sample.repository;

import com.david.sample.model.Joke;

import java.util.List;

public interface JokeRepository {
    List<Joke> getAllJokes();
    void save(Joke joke);
    Joke getJoke(Long id);
    void delete(Long id);
}
