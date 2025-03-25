package lk.ijse.gdse.ormcourseworkproject.Dao.custome;

import lk.ijse.gdse.ormcourseworkproject.Dao.CrudDao;
import lk.ijse.gdse.ormcourseworkproject.Entity.Therapist;
import lk.ijse.gdse.ormcourseworkproject.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface TherapistDao extends CrudDao<Therapist> {
    public Therapist findBy(String therapistId) throws SQLException, ClassNotFoundException ;
}
