package com.david.sample.repository;

import com.david.sample.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


@Repository
public class UserDaoImpl implements UserDao{
    private EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public User findByUserName(String theUserName) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> theQuery = currentSession.createQuery("from User where username=:uName",User.class);
        theQuery.setParameter("uName",theUserName);
        User theUser = null;
        try{
            theUser = theQuery.getSingleResult();
        }catch (Exception e){
            theUser = null;
        }
        return theUser;
    }

    @Override
    public void save(User user) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(user);

    }
}
