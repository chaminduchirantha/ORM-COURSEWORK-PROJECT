package lk.ijse.gdse.ormcourseworkproject.Dao.custome.impl;

import jakarta.persistence.NoResultException;
import lk.ijse.gdse.ormcourseworkproject.Dao.DaoFactory;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.UserDao;
import lk.ijse.gdse.ormcourseworkproject.Entity.User;
import lk.ijse.gdse.ormcourseworkproject.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public User getUser(String name) {
        Session session = FactoryConfiguration.getInstance().getSession();
        User user = null;

        try {
            String hql = "FROM User WHERE userName = :username";
            user = session.createQuery(hql, User.class)
                    .setParameter("username", name)
                    .getSingleResult();
        } catch (NoResultException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return user;
    }

    @Override
    public User getData(String ids) {
        Session session = FactoryConfiguration.getInstance().getSession();
        User user = null;

        try {
            String hql = "FROM User WHERE usernameId = :userId";
            user = session.createQuery(hql, User.class)
                    .setParameter("userId", ids)
                    .getSingleResult();
        } catch (NoResultException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return user;
    }

    @Override
    public String getuserId(String username) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            String hql = "FROM User WHERE userName = :username";
            User user = session.createQuery(hql, User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return user.getUsernameId();
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean updatePassword(String username, String newPassword) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            String hql = "UPDATE User SET userPassword = :password WHERE userName = :username";
            int result = session.createQuery(hql)
                    .setParameter("password", newPassword)
                    .setParameter("username", username)
                    .executeUpdate();

            transaction.commit();
            return result > 0;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
    public String getNextId() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT l.usernameId FROM User l ORDER BY l.usernameId DESC";
        Query<String> query = session.createQuery(hql);

        query.setMaxResults(1);
        String lastId = query.uniqueResult();
        System.out.println("Last ID from DB: " + lastId);
        transaction.commit();
        session.close();

        if (lastId != null) {
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("U%03d", newIdIndex);
        }

        return "U001";

    }

    @Override
    public List<User> getAll() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("SELECT c FROM User c", User.class);
        List<User> customers = query.list();

        transaction.commit();
        session.close();
        return customers;
    }

    @Override
    public boolean save(User user) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User user) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.merge(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String pk) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setUsernameId(pk);
        session.remove(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public User findBy(String pk) throws SQLException, ClassNotFoundException {
        return null;
    }
}



