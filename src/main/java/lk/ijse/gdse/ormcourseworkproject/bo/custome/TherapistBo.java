package lk.ijse.gdse.ormcourseworkproject.bo.custome;

import lk.ijse.gdse.ormcourseworkproject.Dto.TherapistDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface TherapistBo {

    public String getNextId() throws SQLException, IOException ;
    public TherapistDto findBy(String therapistId) throws SQLException, ClassNotFoundException ;
    public List<TherapistDto> getAll();
    public boolean save(TherapistDto therapistDto) ;
    public boolean update(TherapistDto therapistDto) ;
    public boolean delete(String pk) ;
}
