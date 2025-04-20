package lk.ijse.gdse.ormcourseworkproject.Dao.custome;

import lk.ijse.gdse.ormcourseworkproject.Dao.CrudDao;
import lk.ijse.gdse.ormcourseworkproject.Entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;


public interface TherapySessionDao  extends CrudDao<TherapySession> {
    public boolean updateStatus(String sessionId) throws SQLException, ClassNotFoundException, IOException ;
}
