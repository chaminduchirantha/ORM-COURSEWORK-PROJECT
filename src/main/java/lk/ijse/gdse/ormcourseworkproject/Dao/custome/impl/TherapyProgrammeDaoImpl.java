package lk.ijse.gdse.ormcourseworkproject.Dao.custome.impl;

import lk.ijse.gdse.ormcourseworkproject.Dao.DaoFactory;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.TherapyProgrammeDao;
import lk.ijse.gdse.ormcourseworkproject.Entity.Patient;
import lk.ijse.gdse.ormcourseworkproject.Entity.TherapyProgramme;
import lk.ijse.gdse.ormcourseworkproject.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapyProgrammeDaoImpl implements TherapyProgrammeDao {

    public String getNextId() throws SQLException, IOException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        String hql = "SELECT l.therapyProgrammeId FROM TherapyProgramme l ORDER BY l.therapyProgrammeId DESC";
        Query<String> query = session.createQuery(hql);

        query.setMaxResults(1);
        String lastId = query.uniqueResult();

        transaction.commit();
        session.close();

        if (lastId != null) {
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("M%03d", newIdIndex);
        }

        return "M001";
    }
    @Override
    public List<TherapyProgramme> getAll(){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        Query<TherapyProgramme>therapyProgrammeQuery=session.createQuery("SELECT tp FROM TherapyProgramme tp", TherapyProgramme.class);
        List<TherapyProgramme>therapyProgrammeList=therapyProgrammeQuery.list();
        transaction.commit();
        session.close();
        return therapyProgrammeList;
    }

    @Override
    public boolean save(TherapyProgramme therapyProgramme){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        session.persist(therapyProgramme);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(TherapyProgramme therapyProgramme){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        session.merge(therapyProgramme);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String pk){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        TherapyProgramme therapyProgramme=new TherapyProgramme();
        therapyProgramme.setTherapyProgrammeId(pk);
        session.remove( therapyProgramme);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public TherapyProgramme findBy(String therapyProgrammeId) throws SQLException, ClassNotFoundException {
        TherapyProgramme therapyProgramme= null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            therapyProgramme = session.get(TherapyProgramme.class, therapyProgramme);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch the patient by ID: " + therapyProgrammeId);
        }

        return therapyProgramme;
    }

    @Override
    public ArrayList<String> getAllTherapyProgrammeId() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = ("SELECT therapyProgrammeId  FROM TherapyProgramme");

        Query query = session.createQuery(hql);
        ArrayList<String> list = (ArrayList<String>) query.list();

        transaction.commit();
        session.close();
        return list;
    }
}
