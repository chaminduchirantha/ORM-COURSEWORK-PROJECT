package lk.ijse.gdse.ormcourseworkproject.bo.custome;

import lk.ijse.gdse.ormcourseworkproject.Dto.TherapistDto;
import lk.ijse.gdse.ormcourseworkproject.bo.SuperBo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface TherapistBo extends SuperBo {

    public String getNextId() throws SQLException, IOException ;
    public TherapistDto findBy(String therapistId) throws SQLException, ClassNotFoundException ;
    public List<TherapistDto> getAll() throws SQLException, IOException;
    public boolean save(TherapistDto therapistDto) ;
    public boolean update(TherapistDto therapistDto) ;
    public boolean delete(String pk) ;
}
