package dao;

import exceptions.ObjectNotFoundException;
import models.Owner;
import models.Owner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import utils.DataBaseConnection;
import utils.HibernateConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


@Component
public class OwnerDAO extends DataBaseConnection implements DAO<Owner> {

    private static final Logger log = LoggerFactory.getLogger(OwnerDAO.class);
    private static final SessionFactory sessionFactory = HibernateConfiguration.createSessionFactory();

    @Override
    public void create(Owner owner) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            if (owner.getId() == -1) {
                session.persist(owner);
                session.getTransaction().commit();
                log.debug("owner made {}", owner);
            }
            else {
                session.merge(owner);
                log.debug("owner update {}", owner);
            }

        } catch (Exception e) {
            log.error("Error in made {}", owner);
            System.err.println("Error in made owner: " + e.getMessage());
        }

    }

    @Override
    public Owner read(int id) throws ObjectNotFoundException {
        Owner owner = new Owner();
        try ( Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            owner = session.get(Owner.class, id);
            session.getTransaction().commit();
            log.debug("owner was read {}", owner);
        }
        catch (Exception e) {
            log.error("owner was not read {}", owner);
            System.err.println("Ошибка при удалении owner: " + e.getMessage());
        }
        if (owner == null) {
            log.error("owner did not found id = {}", id);
            throw new ObjectNotFoundException();
        }
        return owner;
    }
    @Override
    public void update(Owner owner) {
        try ( Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(owner);
            session.getTransaction().commit();
            log.debug("owner was update {}", owner);
        }
        catch (Exception e) {
            log.error("owner did not update {}", owner);
            System.err.println("Error in update owner" + e.getMessage());
        }

    }
    @Override
    public void remove(int id) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Owner owner = session.get(Owner.class, id);
            session.remove(owner);
            session.getTransaction().commit();
            log.debug("owner was remove {}", owner);
        } catch (Exception e) {
            log.error("owner was not remove id = {}", id);
            System.err.println("Ошибка при удалении owner: " + e.getMessage());
        }
    }

}
