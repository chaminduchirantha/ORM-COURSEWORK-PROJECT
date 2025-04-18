package lk.ijse.gdse.ormcourseworkproject.Dao.custome.impl;

import lk.ijse.gdse.ormcourseworkproject.Dao.DaoFactory;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.UserDao;
import lk.ijse.gdse.ormcourseworkproject.Entity.User;
import lk.ijse.gdse.ormcourseworkproject.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    Session session = FactoryConfiguration.getInstance().getSession();
    Transaction transaction = session.beginTransaction();
    User user = (User) DaoFactory.getInstance().getDao(DaoFactory.daoType.USER);


    @Override
    public String getNextId() throws SQLException, IOException {
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
        Query<User> query = session.createQuery("SELECT c FROM User c", User.class);
        List<User> customers = query.list();

        transaction.commit();
        session.close();
        return customers;
    }

    @Override
    public boolean save(User user) {
        session.persist(user);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean update(User user) {
        session.merge(user);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean delete(String pk) {
        user.setUsernameId(pk);
        session.remove(user);
        transaction.commit();
        session.close();
        return false;
    }
}



