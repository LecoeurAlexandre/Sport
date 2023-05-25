package org.example.service;

import org.example.entities.Activity;
import org.example.entities.FavoriteSport;
import org.example.interfaces.Repository;

import java.util.List;

public class SportService extends BaseService implements Repository<FavoriteSport> {
    @Override
    public boolean create(FavoriteSport o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(FavoriteSport o) {
        return false;
    }

    @Override
    public boolean delete(FavoriteSport o) {
        return false;
    }

    @Override
    public FavoriteSport findById(int id) {
        FavoriteSport favSport = null;
        session = sessionFactory.openSession();
        favSport = (FavoriteSport) session.get(FavoriteSport.class, id);
        session.close();
        return favSport;
    }

    @Override
    public List<FavoriteSport> findAll() {
        return null;
    }

    @Override
    public void end() {
        sessionFactory.close();
    }
}
