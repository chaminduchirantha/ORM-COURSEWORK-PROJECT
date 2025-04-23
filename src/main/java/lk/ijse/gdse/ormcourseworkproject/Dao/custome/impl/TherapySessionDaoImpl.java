package lk.ijse.gdse.ormcourseworkproject.Dao.custome.impl;

import lk.ijse.gdse.ormcourseworkproject.Dao.DaoFactory;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.TherapySessionDao;
import lk.ijse.gdse.ormcourseworkproject.Entity.TherapySession;
import lk.ijse.gdse.ormcourseworkproject.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TherapySessionDaoImpl implements TherapySessionDao {


    @Override
    public String getNextId() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        String hql = "SELECT l.sessionID FROM TherapySession l ORDER BY l.sessionID DESC";
        Query<String> query = session.createQuery(hql);

        query.setMaxResults(1);
        String lastId = query.uniqueResult();

        transaction.commit();
        session.close();

        if (lastId != null) {
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("S%03d", newIdIndex);
        }

        return "S001";
    }

    @Override
    public List<TherapySession> getAll(){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        Query<TherapySession>therapySessionQuery = session.createQuery("SELECT s from TherapySession s", TherapySession.class);
        List<TherapySession>therapySessions=therapySessionQuery.list();
        transaction.commit();
        return therapySessions;

    }
    @Override
    public boolean save(TherapySession therapySession) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        session.persist(therapySession);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(TherapySession therapySession) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        session.merge(therapySession);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String pk){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        TherapySession therapySession = new TherapySession();
        therapySession.setSessionID(pk);
        session.persist(therapySession);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public TherapySession findBy(String pk) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updateStatus(String sessionId) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("UPDATE TherapySession ts SET ts = 'Completed' WHERE ts.sessionID = :sessionId");
        query.setParameter("sessionId", sessionId);
        query.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }
}
