package ru.job4j.todolist.persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.todolist.model.Item;

import java.time.LocalDateTime;
import java.util.List;

public class TodoStore implements Store, AutoCloseable {

    private final static TodoStore INSTANCE = new TodoStore();

    public static TodoStore getInstance() {
        return INSTANCE;
    }

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    private Session session = null;
    private Transaction transaction = null;

    @Override
    public void addTask(Item item) {
        try {
            connectionWork();
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
    public void updateTask(String id, Item item) {
        try {
            int itemId = Integer.parseInt(id);
            connectionWork();
            item = session.get(Item.class, itemId);
            session.update(item);
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
    public Item getById(Long id) {
        connectionWork();
        return session.get(Item.class, id);
    }

    @Override
    public List<Item> getAllTask() {
        connectionWork();
        return session.createQuery("from Item").list();
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    private void connectionWork() throws HibernateException {
        session = sf.openSession();
        transaction = session.beginTransaction();
    }

    public static void main(String[] args) {
        Item item = new Item();
        TodoStore.getInstance().addTask(new Item("sTest", LocalDateTime.now(), true));
        item.setDone(true);
        List<Item> result = TodoStore.getInstance().getAllTask();
        System.out.println(result);
    }
}