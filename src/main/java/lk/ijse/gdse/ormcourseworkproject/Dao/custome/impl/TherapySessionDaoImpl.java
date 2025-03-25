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

    Session session = FactoryConfiguration.getInstance().getSession();
    Transaction transaction=session.beginTransaction();

    TherapySession therapySession = (TherapySession) DaoFactory.getInstance().getDao(DaoFactory.daoType.THERAPYSESSION);


    @Override
    public String getNextId() throws SQLException, IOException {
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
            return String.format("T%03d", newIdIndex);
        }

        return "T001";
    }

    @Override
    public List<TherapySession> getAll(){
        Query<TherapySession>therapySessionQuery = session.createQuery("SELECT s from TherapySession s", TherapySession.class);
        List<TherapySession>therapySessions=therapySessionQuery.list();
        transaction.commit();
        return therapySessions;

    }
    @Override
    public void save(TherapySession therapySession) {
        session.persist(therapySession);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(TherapySession therapySession) {
        session.merge(therapySession);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(String pk){
        therapySession.setSessionID(pk);
        session.persist(therapySession);
        transaction.commit();
        session.close();
    }
}
