package lk.ijse.gdse.ormcourseworkproject.Dao.custome.impl;

import lk.ijse.gdse.ormcourseworkproject.Dao.DaoFactory;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.PatientDao;
import lk.ijse.gdse.ormcourseworkproject.Entity.Patient;
import lk.ijse.gdse.ormcourseworkproject.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PatientDaoImpl implements PatientDao {

    @Override
    public String getNextId() throws SQLException, IOException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        String hql = "SELECT l.patientId FROM Patient l ORDER BY l.patientId DESC";
        Query<String> query = session.createQuery(hql);

        query.setMaxResults(1);
        String lastId = query.uniqueResult();

        transaction.commit();
        session.close();

        if (lastId != null) {
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("P%03d", newIdIndex);
        }

        return "P001";
    }

    @Override
    public List<Patient> getAll(){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        Query<Patient>patientQuery=session.createQuery("SELECT p from Patient p" ,Patient.class );
        List<Patient> patientList=patientQuery.list();
        transaction.commit();
        session.close();
        return patientList;
    }

    @Override
    public boolean save(Patient patient){

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        session.persist(patient);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update (Patient patient){

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        session.merge(patient);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String pk){

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        Patient patient = new Patient();
        patient.setPatientId(pk);
        session.remove(patient);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Patient findBy(String patientId) throws SQLException, ClassNotFoundException {
        Patient patient = null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            patient = session.get(Patient.class, patientId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch the patient by ID: " + patientId);
        }

        return patient;
    }
}
