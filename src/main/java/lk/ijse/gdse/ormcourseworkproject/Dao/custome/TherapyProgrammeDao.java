package lk.ijse.gdse.ormcourseworkproject.Dao.custome;

import lk.ijse.gdse.ormcourseworkproject.Dao.CrudDao;
import lk.ijse.gdse.ormcourseworkproject.Entity.TherapyProgramme;
import lk.ijse.gdse.ormcourseworkproject.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface TherapyProgrammeDao extends CrudDao<TherapyProgramme> {
    public ArrayList<String> getAllTherapyProgrammeId() throws SQLException, ClassNotFoundException, IOException ;
    public TherapyProgramme findBy(String therapyProgrammeId) throws SQLException, ClassNotFoundException ;
}
