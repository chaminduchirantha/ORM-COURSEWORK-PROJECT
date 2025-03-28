package lk.ijse.gdse.ormcourseworkproject.bo.custome;

import lk.ijse.gdse.ormcourseworkproject.Dao.custome.TherapyProgrammeDao;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.impl.TherapyProgrammeDaoImpl;
import lk.ijse.gdse.ormcourseworkproject.Dto.TherapyProgrammeDto;
import lk.ijse.gdse.ormcourseworkproject.Entity.TherapyProgramme;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TherapyProgrammeBo {
    public String getNextId() throws SQLException, IOException ;
    public List<TherapyProgramme> getAll();
    public boolean save(TherapyProgrammeDto therapyProgrammeDto);
    public boolean update(TherapyProgrammeDto therapyProgrammeDto);
    public boolean delete(String pk);
}
