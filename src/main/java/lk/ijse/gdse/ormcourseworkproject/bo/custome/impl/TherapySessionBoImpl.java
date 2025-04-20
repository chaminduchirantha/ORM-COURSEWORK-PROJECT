package lk.ijse.gdse.ormcourseworkproject.bo.custome.impl;

import lk.ijse.gdse.ormcourseworkproject.Dao.custome.TherapySessionDao;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.impl.TherapySessionDaoImpl;
import lk.ijse.gdse.ormcourseworkproject.Dto.TherapySessionDto;
import lk.ijse.gdse.ormcourseworkproject.Entity.TherapySession;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.TherapySessionBo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapySessionBoImpl implements TherapySessionBo {
    TherapySessionDao therapySessionDao = new TherapySessionDaoImpl();
    @Override
    public String getNextId() throws SQLException, IOException {
        return therapySessionDao.getNextId();
    }


    @Override
    public List<TherapySessionDto> getAll() throws SQLException, IOException {
        List<TherapySession> therapySessions=therapySessionDao.getAll();
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
            }

            return therapySessionDtos;
        }


    @Override
    public boolean save(TherapySessionDto therapySessionDto) {
        return therapySessionDao.save(new TherapySession(therapySessionDto.getTherapySessionId(),therapySessionDto.getDate(),therapySessionDto.getTime(),therapySessionDto.getSessionName(),therapySessionDto.getPatientId(),therapySessionDto.getTherapistId()));
    }

    public boolean updateStatus(String sessionId) throws SQLException, ClassNotFoundException, IOException {
       return therapySessionDao.updateStatus(sessionId);
    }

    @Override
    public boolean update(TherapySessionDto therapySessionDto) {
       return therapySessionDao.update(new TherapySession(therapySessionDto.getTherapySessionId(),therapySessionDto.getDate(),therapySessionDto.getTime(),therapySessionDto.getSessionName(),therapySessionDto.getPatientId(),therapySessionDto.getTherapistId()));
    }

    @Override
    public boolean delete(String pk){
        return therapySessionDao.delete(pk);
    }
}
