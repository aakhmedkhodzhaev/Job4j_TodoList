package ru.job4j.todolist.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.todolist.model.Item;
import ru.job4j.todolist.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class TodoRegistration implements Registration, AutoCloseable {

    private final static TodoRegistration INSTANCE = new TodoRegistration();

    public final static TodoRegistration getInstance() {
        return INSTANCE;
    }

    private static final Logger Log = Logger.getLogger(Item.class.toString());

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();


    @Override
    public void save(User user) {
        if (user.getId() == null) {
            createUser(user);
        } else {
            updateUser(user);
        }
    }

    private void createUser(User user) {
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }


    private void updateUser(User user) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public boolean dropUser(Long id) {
        Boolean status;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            if (id != null) {
                User result = session.get(User.class, id);
                session.delete(result);
                session.getTransaction().commit();
                status = true;
            } else {
                status = false;
            }
            return status;
        }
    }

    @Override
    public User findByEmail(String email) {
        Collection<User>result;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.createQuery("from User u where u.email = :email").setParameter("email", email).getResultList();
            return (User) result;
        }
    }

    @Override
    public User getById(Long id) {
        User user;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            user = session.get(User.class, id);
        }
        return user;
    }

    @Override
    public User getByData(final String email, final String password) {
        User user;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            user = (User) session.createQuery("from User t where t.email = :email and t.password = :password").setParameter(email, password).getResultList();
        }
        return user;
    }

    @Override
    public Collection<User> AllUser() {
        Collection<User> result;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.createQuery("From User Order by 1").getResultList();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

}