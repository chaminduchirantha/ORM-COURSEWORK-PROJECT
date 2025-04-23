package lk.ijse.gdse.ormcourseworkproject.bo.custome;

import lk.ijse.gdse.ormcourseworkproject.Dto.PatientDto;
import lk.ijse.gdse.ormcourseworkproject.Entity.Patient;
import lk.ijse.gdse.ormcourseworkproject.bo.SuperBo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PatientBo extends SuperBo {

    public boolean save(PatientDto patientDTO) throws IOException, SQLException;
    public String getNextId() throws SQLException, IOException;
    public List<PatientDto> getAll() throws SQLException, IOException;
    public boolean update(PatientDto patientDTO) throws IOException, SQLException;
    public boolean delete(String ID) throws SQLException, IOException;
    public PatientDto findBy(String patientId) throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllPatientIDs() throws SQLException, ClassNotFoundException, IOException ;
    public int getTotalPatients() throws SQLException, ClassNotFoundException, IOException;
    public List<Object[]> getPatientsBySessionId(String sessionId) throws SQLException, ClassNotFoundException;
}
