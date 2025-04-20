package lk.ijse.gdse.ormcourseworkproject.Dao.custome.impl;

import lk.ijse.gdse.ormcourseworkproject.Dao.DaoFactory;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.TherapistDao;
import lk.ijse.gdse.ormcourseworkproject.Entity.Therapist;
import lk.ijse.gdse.ormcourseworkproject.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TherapistDaoImpl implements TherapistDao {


    @Override
    public String getNextId() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT l.therapistId FROM Therapist l ORDER BY l.therapistId DESC";
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
    public Therapist findBy(String therapistId) throws SQLException, ClassNotFoundException {
        Therapist therapist = null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            therapist = session.get(Therapist.class, therapistId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch the therapist by ID: " + therapistId);
        }

        return therapist;
    }

    @Override
    public List<Therapist> getAll(){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query<Therapist>therapistQuery=session.createQuery("SELECT t FROM Therapist t", Therapist.class);
        List<Therapist>therapists = therapistQuery.list();
        transaction.commit();
        session.close();
        return therapists;
    }

    @Override
    public boolean save(Therapist therapist) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(therapist);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Therapist therapist) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.merge(therapist);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String pk) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Therapist therapist = new Therapist();
        therapist.setTherapistId(pk);
        session.remove(therapist);
        transaction.commit();
        session.close();
        return true;
    }
}
