package ru.job4j.todolist.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.todolist.model.Category;
import ru.job4j.todolist.model.Item;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;

public class TodoStore implements Store, AutoCloseable {

    private final static TodoStore INSTANCE = new TodoStore();

    public final static TodoStore getInstance() {
        return INSTANCE;
    }

    private static final Logger Log = Logger.getLogger(Item.class.toString());

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();


    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }


    @Override
    public Serializable addTask(Item item, String[] cids) {
        item.setCreated(LocalDate.now());
        item.setDone(false);
        for (String id : cids) {
            Category category = tx(session -> session.find(Category.class, Long.parseLong(id)));
            item.addCategory(category);
        }
        return this.tx(session -> session.save(item));
    }

    @Override
    public void updateTask(Item item) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            if (item.getDone() == true) {
                item.setDone(false);
            } else {
                item.setDone(true);
            }
            session.update(item);
            session.getTransaction().commit();
        }
    }

    @Override
    public boolean delete(Item item) {
        Boolean result;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            if (item != null) {
                Item items = session.get(Item.class, item.getId());
                session.delete(items);
                result = true;
                session.getTransaction().commit();
            } else {
                result = false;
            }
        }
        return result;
    }

    @Override
    public Item getById(Long id) {
        return this.tx(session -> session.get(Item.class, id));
    }

    @Override
    public Collection<Item> getStatusTask(Boolean status) {
        return this.tx(session -> session.createQuery("from Item a where a.done = :done order by 1").setParameter("done", status).list()); //.getResultList())result;);
    }

    @Override
    public Collection<Item> getAllTask() {
        return this.tx(session -> session.createQuery("from Item order by 1").list());
    }

    @Override
    public List<Category> getAllCategory() {
        return this.tx(session -> session.createQuery("from Category order by 1").list());
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

}