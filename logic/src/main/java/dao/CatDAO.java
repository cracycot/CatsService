package dao;

import exceptions.ObjectNotFoundException;
import models.Cat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import utils.DataBaseConnection;



public class CatDAO extends DataBaseConnection implements DAO<Cat> {

    private  static final Configuration configuration = new Configuration().addAnnotatedClass(Cat.class).configure();
    private static final Logger log = LoggerFactory.getLogger(CatDAO.class);
    private static final SessionFactory sessionFactory = configuration.buildSessionFactory();


    @Override
    public void create(Cat cat) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            if (cat.getId() == -1) {
                session.persist(cat);
                session.getTransaction().commit();
                log.debug("Cat made {}", cat);
            }
            else {
                session.merge(cat);
                log.debug("Cat update {}", cat);
            }

        } catch (Exception e) {
            log.error("Error in made {}", cat);
            System.err.println("Error in made Cat: " + e.getMessage());
        }

    }

    @Override
    public Cat read(int id) throws ObjectNotFoundException {
        Cat cat = new Cat();
        try ( Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            cat = session.get(Cat.class, id);
            session.getTransaction().commit();
            log.debug("Cat was read {}", cat);
        }
        catch (Exception e) {
            log.error("Cat was not read {}", cat);
            System.err.println("Ошибка при удалении Cat: " + e.getMessage());
        }
        if (cat == null) {
            log.error("Cat did not found id = {}", id);
            throw new ObjectNotFoundException();
        }
        return cat;
    }
    @Override
    public void update(Cat cat) {
        try ( Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(cat);
            session.getTransaction().commit();
            log.debug("Cat was update {}", cat);
        }
        catch (Exception e) {
            log.error("Cat did not update {}", cat);
            System.err.println("Error in update cat" + e.getMessage());
        }

    }
    @Override
    public void remove(int id) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Cat cat = session.get(Cat.class, id);
            session.remove(cat);
            session.getTransaction().commit();
            log.debug("Cat was remove {}", cat);
        } catch (Exception e) {
            log.error("Cat was not remove id = {}", id);
            System.err.println("Ошибка при удалении Cat: " + e.getMessage());
        }
    }
}
