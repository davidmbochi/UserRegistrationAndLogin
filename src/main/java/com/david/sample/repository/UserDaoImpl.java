package com.david.sample.repository;

import com.david.sample.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;



@Repository
public class UserDaoImpl implements UserDao{
    private final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    private EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public User findByUserName(String theUserName) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> theQuery = currentSession.createQuery("from User where userName=:uName",User.class);
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
