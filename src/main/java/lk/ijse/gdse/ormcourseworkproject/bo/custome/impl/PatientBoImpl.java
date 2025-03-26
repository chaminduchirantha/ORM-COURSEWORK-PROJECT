package lk.ijse.gdse.ormcourseworkproject.bo.custome.impl;

import lk.ijse.gdse.ormcourseworkproject.Dao.custome.PatientDao;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.impl.PatientDaoImpl;
import lk.ijse.gdse.ormcourseworkproject.Dto.PatientDto;
import lk.ijse.gdse.ormcourseworkproject.Entity.Patient;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.PatientBo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientBoImpl implements PatientBo {
    PatientDao patientDao = new PatientDaoImpl();

    @Override
    public String getNextId() throws SQLException, IOException {
        return patientDao.getNextId();
    }

    @Override
    public List<PatientDto> getAll(){
        List<Patient>patients = new ArrayList<>();
        List<PatientDto> patientDtos = new ArrayList<>();
        for(Patient patient:patients){
            PatientDto patientDto = new PatientDto();
            patientDto.setPatientID(patient.getPatientID());
            patientDto.setPatientName(patient.getPatientName());
            patientDto.setGender(patient.getGender());
            patientDto.setBirthDate(patient.getBirthDate());
            patientDto.setAddress(patient.getAddress());
            patientDto.setPhone(patient.getPhone());
            patientDtos.add(patientDto);
        }
        return patientDtos;
    }

    @Override
    public boolean save(PatientDto patientDto){
       return patientDao.save(new Patient(patientDto.getPatientID(),patientDto.getPatientName(),patientDto.getGender(),patientDto.getBirthDate(),patientDto.getAddress(),patientDto.getPhone(),patientDto.getMedicalHistory()));
    }

    @Override
    public boolean update (PatientDto patientDto){
        return patientDao.update(new Patient(patientDto.getPatientID(),patientDto.getPatientName(),patientDto.getGender(),patientDto.getBirthDate(),patientDto.getAddress(),patientDto.getPhone(),patientDto.getMedicalHistory()));
    }

    @Override
    public boolean delete(String pk){
        return patientDao.delete(pk);
    }

    @Override
    public PatientDto findBy(String patientId) throws SQLException, ClassNotFoundException {
       Patient patient = patientDao.findBy(patientId);
       return new PatientDto(patient.getPatientID(),patient.getPatientName(),patient.getGender(),patient.getBirthDate(),patient.getAddress(),patient.getPhone(),patient.getMedicalHistory());
    }
}
