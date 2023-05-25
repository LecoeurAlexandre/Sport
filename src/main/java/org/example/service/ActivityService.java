package org.example.service;

import org.example.entities.Activity;
import org.example.entities.User;
import org.example.interfaces.Repository;
import org.hibernate.query.Query;

import java.util.List;

public class ActivityService extends BaseService implements Repository<Activity> {
    public ActivityService(){
        super();
    }
    @Override
    public boolean create(Activity o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Activity o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Activity o) {
        return false;
    }

    @Override
    public Activity findById(int id) {
        Activity activity = null;
        session = sessionFactory.openSession();
        activity = (Activity) session.get(Activity.class, id);
        session.close();
        return activity;
    }

    @Override
    public List<Activity> findAll() {
        List<Activity> activityList = null;
        session = sessionFactory.openSession();
        Query<Activity> activityQuery = session.createQuery("from Activity");
        activityList = activityQuery.list();
        session.close();
        return activityList;
    }

    @Override
    public void end() {
        sessionFactory.close();
    }

    public List<Activity> findByAvailability() {
        List<Activity> activityList = null;
        session = sessionFactory.openSession();
        Query<Activity> activityQuery = session.createQuery("from Activity where registrationsAvailable > 0");
        activityList = activityQuery.list();
        session.close();
        return activityList;
    }
}
