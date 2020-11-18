package ru.job4j.todolist.persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.todolist.model.Item;

import java.time.LocalDate;
import java.util.Collection;
import java.util.logging.Logger;

public class TodoStore implements Store, AutoCloseable {

    private final static TodoStore INSTANCE = new TodoStore();

    public static TodoStore getInstance() {
        return INSTANCE;
    }

    private static final Logger Log = Logger.getLogger(Item.class.toString());

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    private Session session = null;
    private Transaction transaction = null;

    @Override
    public void addTask(Item item) {
        try {
            connectionWork();
            item.setCreated(LocalDate.now());
            item.setDone(false);
            session.save(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void updateTask(String id, Boolean done) {
        try {
            connectionWork();
            Long itemId = Long.valueOf(id);
            Item result = session.get(Item.class, itemId);
            result.setDone(done);
            session.update(result);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String id) {
        Boolean result;
        try {
            connectionWork();
            long itemId = Long.valueOf(id);
            Item item = new Item();
            item.setId(itemId);
            if (item != null) {
                session.delete(item);
                transaction.commit();
                result = true;
            } else {
                result = false;
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            result = false;
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Item getById(Long id) {
        connectionWork();
        Item result = session.get(Item.class, id);
        session.close();
        return result;
    }

    @Override
    public Collection<Item> getStatusTask(Boolean status) {
        connectionWork();
        Collection<Item> result = session.createQuery("from Item a where a.done = :done").setParameter("done", status).list(); //.getResultList()
        session.close();
        return result;
    }

    @Override
    public Collection<Item> getAllTask() {
        connectionWork();
        Collection<Item> result = session.createQuery("from Item order by 1").list();
        session.close();
        return result;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    private final void connectionWork() throws HibernateException {
        session = sf.openSession();
        transaction = session.beginTransaction();
    }
}