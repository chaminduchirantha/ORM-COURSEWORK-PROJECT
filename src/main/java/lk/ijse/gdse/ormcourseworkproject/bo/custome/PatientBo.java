package lk.ijse.gdse.ormcourseworkproject.bo.custome;

import lk.ijse.gdse.ormcourseworkproject.Dto.PatientDto;
import lk.ijse.gdse.ormcourseworkproject.Entity.Patient;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PatientBo {

    public String getNextId() throws SQLException, IOException ;
    public List<PatientDto> getAll() throws SQLException, IOException;
    public boolean save(PatientDto patientDto);
    public boolean update (PatientDto patientDto);
    public boolean delete(String pk);
    public PatientDto findBy(String patientId) throws SQLException, ClassNotFoundException ;
}
