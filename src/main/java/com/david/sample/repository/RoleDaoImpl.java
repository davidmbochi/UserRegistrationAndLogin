package com.david.sample.repository;

import com.david.sample.model.Role;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class RoleDaoImpl implements RoleDao{
    private EntityManager entityManager;

    public RoleDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    public Role findRoleByName(String theRoleName) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Role> theQuery = currentSession.createQuery("from Role where name=:roleName",Role.class);
        theQuery.setParameter("roleName",theRoleName);

        Role theRole = null;
        try{
            theRole= theQuery.getSingleResult();
        }catch (Exception e){
            theRole = null;
        }
        return theRole;

    }
}
