package lk.ijse.gdse.ormcourseworkproject.bo.custome.impl;

import lk.ijse.gdse.ormcourseworkproject.Dao.DaoFactory;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.PatientDao;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.impl.PatientDaoImpl;
import lk.ijse.gdse.ormcourseworkproject.Dto.PatientDto;
import lk.ijse.gdse.ormcourseworkproject.Entity.Patient;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.PatientBo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientBoImpl implements PatientBo {

    PatientDao patientDao = (PatientDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.PATIENT);
    @Override
    public boolean save(PatientDto patientDTO) {
        System.out.println("PatientBOImpl Saving ID: " + patientDTO.getPatientId()); // Debugging

        return patientDao.save(new Patient(patientDTO.getPatientId(),patientDTO.getName(),patientDTO.getBirthday(),patientDTO.getContactNumber(),patientDTO.getMedicalHistory()));
    }

    @Override
    public String getNextId() throws SQLException, IOException {
        return patientDao.getNextId();
    }

    @Override
    public List<PatientDto> getAll() throws SQLException, IOException {
        List<Patient> patients = patientDao.getAll();
        List<PatientDto> patientDTOS = new ArrayList<>();

        for (Patient patient : patients) {
            PatientDto patientDTO = new PatientDto();
            patientDTO.setPatientId(patient.getPatientId());
            patientDTO.setName(patient.getName());
            patientDTO.setBirthday(patient.getBirthday());
            patientDTO.setContactNumber(patient.getContactNumber());
            patientDTO.setMedicalHistory(patient.getMedicalHistory());

            patientDTOS.add(patientDTO);
        }
        return patientDTOS;
    }

    @Override
    public boolean update(PatientDto patientDTO) throws IOException, SQLException {
        return patientDao.update(new Patient(patientDTO.getPatientId(),patientDTO.getName(),patientDTO.getBirthday(),patientDTO.getContactNumber(),patientDTO.getMedicalHistory()));

    }

    @Override
    public boolean delete(String ID) throws SQLException, IOException {
        return patientDao.delete(ID);
    }

    @Override
    public PatientDto findBy(String patientId) throws SQLException, ClassNotFoundException {
        Patient patient = patientDao.findBy(patientId);
        return new PatientDto(patient.getPatientId(), patient.getName(), patient.getBirthday(), patient.getContactNumber(), patient.getMedicalHistory());
    }

    @Override
    public ArrayList<String> getAllPatientIDs() throws SQLException, ClassNotFoundException, IOException {
        ArrayList<String> allIds = new ArrayList<>();
        ArrayList<String>all = patientDao.getAllPatientIDs();
        for(String id: all){
            allIds.add(id);
        }
        return allIds;
    }

    @Override
    public int getTotalPatients() throws SQLException, ClassNotFoundException, IOException {
        return 0;
    }

    @Override
    public List<Object[]> getPatientsBySessionId(String sessionId) throws SQLException, ClassNotFoundException {
        return List.of();
    }

//    @Override
//    public ArrayList<String> getAllPatientIds() throws SQLException, ClassNotFoundException, IOException {
//        ArrayList<String> allIds = new ArrayList<>();
//        ArrayList<String>all = patientDao.getAllPatientIDs();
//        for(String p: all){
//            allIds.add(p);
//
//        }
//        return allIds;
//    }

//    @Override
//    public int getTotalPatients() throws SQLException, ClassNotFoundException, IOException {
//        return patientDao.getTotalPatients();
//    }

//    @Override
//    public List<Object[]> getPatientsBySessionId(String sessionId) throws SQLException, ClassNotFoundException, IOException {
//        return List.of();
//    }

}
