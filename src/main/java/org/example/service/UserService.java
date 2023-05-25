package org.example.service;

import org.example.entities.Activity;
import org.example.entities.User;
import org.example.interfaces.Repository;

import java.util.List;

public class UserService extends BaseService implements Repository<User> {
    public UserService(){
        super();
    }

    @Override
    public boolean create(User o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User o) {
        return false;
    }

    @Override
    public boolean delete(User o) {
        return false;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
