package com.david.sample.repository;

import com.david.sample.model.Joke;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;
@Repository
public class JokeRepositoryImpl implements JokeRepository{
    private final EntityManager entityManager;

    @Autowired
    public JokeRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    public List<Joke> getAllJokes() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery = currentSession.createQuery("from Joke",Joke.class);
        List<Joke> jokesList = theQuery.getResultList();
        return jokesList;
    }

    @Override
    public void save(Joke joke) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(joke);
    }

    @Override
    public Joke getJoke(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Joke joke = currentSession.get(Joke.class,id);
        return joke;
    }

    @Override
    public void delete(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("delete from  Joke where id=:jokeId");

        theQuery.setParameter("jokeId",id);

        theQuery.executeUpdate();
    }
}
