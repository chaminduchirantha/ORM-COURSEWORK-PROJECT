package lk.ijse.gdse.ormcourseworkproject.Dao.custome;

import lk.ijse.gdse.ormcourseworkproject.Dao.CrudDao;
import lk.ijse.gdse.ormcourseworkproject.Entity.Patient;
import lk.ijse.gdse.ormcourseworkproject.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PatientDao extends CrudDao<Patient> {
    public Patient findBy(String patientId) throws SQLException, ClassNotFoundException ;
    public ArrayList<String> getAllPatientIDs() throws SQLException, ClassNotFoundException, IOException ;
}
