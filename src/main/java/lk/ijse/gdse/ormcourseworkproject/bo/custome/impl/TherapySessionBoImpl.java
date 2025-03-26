package lk.ijse.gdse.ormcourseworkproject.bo.custome.impl;

import lk.ijse.gdse.ormcourseworkproject.Dao.custome.TherapySessionDao;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.impl.TherapySessionDaoImpl;
import lk.ijse.gdse.ormcourseworkproject.Dto.TherapySessionDto;
import lk.ijse.gdse.ormcourseworkproject.Entity.TherapySession;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapySessionBoImpl {
    public String getNextId() throws SQLException, IOException {
        TherapySessionDao therapySessionDao = new TherapySessionDaoImpl();
        return therapySessionDao.getNextId();
    }


    public List<TherapySessionDto> getAll() {
        TherapySessionDao therapySessionDao = new TherapySessionDaoImpl();
        List<TherapySession> therapySessions = new ArrayList<>();
        List<TherapySessionDto> therapySessionDtos = new ArrayList<>();

        for (TherapySession therapySession : therapySessions) {
            TherapySessionDto therapySessionDto = new TherapySessionDto();
            therapySessionDto.setTherapySessionId(therapySession.getSessionID());
            therapySessionDto.setSessionName(therapySession.getSessionName());
            therapySessionDto.setDate(therapySession.getDate());
            therapySessionDto.setTime(therapySession.getTime());
            therapySessionDto.setPatientId(therapySessionDto.getPatientId());
            therapySessionDto.setTherapistId(therapySessionDto.getTherapistId());
            therapySessionDtos.add(therapySessionDto);
//            if (therapySession.getTherapist() != null) {
//                therapySessionDto.setTherapistId(therapySession.getTherapist().getTherapistId());
//            } else {
//                therapySessionDto.setTherapistId("N/A");
//            }
//            if (therapySession.getPatient() != null) {
//                therapySessionDto.setPatientId(therapySession.getPatient().getPatientID());
//            } else {
//                therapySessionDto.setPatientId("N/A");
//            }
//            therapySessionDtos.add(therapySessionDto);
            }

            return therapySessionDtos;
        }


    public boolean save(TherapySessionDto therapySessionDto) {
        TherapySessionDao therapySessionDao = new TherapySessionDaoImpl();
        return therapySessionDao.save(new TherapySession(therapySessionDto.getTherapySessionId(),therapySessionDto.getDate(),therapySessionDto.getTime(),therapySessionDto.getSessionName(),therapySessionDto.getPatientId(),therapySessionDto.getTherapistId()));
    }

    public boolean update(TherapySessionDto therapySessionDto) {
       TherapySessionDao  therapySessionDao = new TherapySessionDaoImpl();
       return therapySessionDao.update(new TherapySession(therapySessionDto.getTherapySessionId(),therapySessionDto.getDate(),therapySessionDto.getTime(),therapySessionDto.getSessionName(),therapySessionDto.getPatientId(),therapySessionDto.getTherapistId()));
    }

    public boolean delete(String pk){
        TherapySessionDao therapySessionDao = new TherapySessionDaoImpl();
        return therapySessionDao.delete(pk);
    }
}
