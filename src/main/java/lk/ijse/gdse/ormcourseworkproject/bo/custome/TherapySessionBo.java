package lk.ijse.gdse.ormcourseworkproject.bo.custome;
import lk.ijse.gdse.ormcourseworkproject.Dto.TherapySessionDto;
import lk.ijse.gdse.ormcourseworkproject.bo.SuperBo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface TherapySessionBo extends SuperBo {
    public String getNextId() throws SQLException, IOException;
    public List<TherapySessionDto> getAll() throws SQLException, IOException;
    public boolean save(TherapySessionDto therapySessionDto);
    public boolean update(TherapySessionDto therapySessionDto) ;
    public boolean delete(String pk);
    public boolean updateStatus(String sessionId) throws SQLException, ClassNotFoundException, IOException ;
}

