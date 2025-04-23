package lk.ijse.gdse.ormcourseworkproject.bo.custome.impl;

import lk.ijse.gdse.ormcourseworkproject.Dao.DaoFactory;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.TherapySessionDao;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.impl.TherapySessionDaoImpl;
import lk.ijse.gdse.ormcourseworkproject.Dto.TherapySessionDto;
import lk.ijse.gdse.ormcourseworkproject.Entity.Patient;
import lk.ijse.gdse.ormcourseworkproject.Entity.TherapyProgramme;
import lk.ijse.gdse.ormcourseworkproject.Entity.TherapySession;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.TherapySessionBo;
import lk.ijse.gdse.ormcourseworkproject.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapySessionBoImpl implements TherapySessionBo {
    TherapySessionDao therapySessionDao = (TherapySessionDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.THERAPYSESSION);

    @Override
    public String getNextId() throws SQLException, IOException {
        return therapySessionDao.getNextId();
    }


    @Override
    public List<TherapySessionDto> getAll() throws SQLException, IOException {
        List<TherapySession> therapySessions = therapySessionDao.getAll();
        List<TherapySessionDto> therapySessionDtos = new ArrayList<>();

        for (TherapySession therapySession : therapySessions) {
            TherapySessionDto therapySessionDto = new TherapySessionDto();
            therapySessionDto.setTherapySessionId(therapySession.getSessionID());
            therapySessionDto.setSessionName(therapySession.getSessionName());
            therapySessionDto.setDate(therapySession.getDate());
            therapySessionDto.setTime(therapySession.getTime());
            therapySessionDto.setPatientId(therapySessionDto.getPatientId());
//            therapySessionDto.setTherapyProgrammeId(therapySessionDto.getTherapyProgrammeId());
            therapySessionDtos.add(therapySessionDto);
        }

        return therapySessionDtos;
    }


    @Override
    public boolean save(TherapySessionDto therapySessionDto) {
        Session session = FactoryConfiguration.getInstance().getSession();

        TherapyProgramme therapist = session.get(TherapyProgramme.class, therapySessionDto.getTherapyProgrammeId());
        Patient patient = session.get(Patient.class, therapySessionDto.getPatientId());
        if (therapist == null || patient == null) {
            return false;
        }

        TherapySession therapySession = new TherapySession(
                therapySessionDto.getTherapySessionId(),
                therapySessionDto.getSessionName(),
                therapySessionDto.getDate(),
                therapySessionDto.getTime(),
                patient,
                therapist
        );

        return therapySessionDao.save(therapySession);
    }

    public boolean updateStatus(String sessionId) throws SQLException, ClassNotFoundException, IOException {
       return therapySessionDao.updateStatus(sessionId);
    }

    @Override
    public boolean update(TherapySessionDto therapySessionDto) {
//        Session session = FactoryConfiguration.getInstance().getSession();
//
//        Patient patient = session.get(Patient.class, therapySessionDto.getPatientId());
//        if (patient == null) {
//            return false;
//        }
//
//
//        TherapySession therapySession = new TherapySession(
//                therapySessionDto.getTherapySessionId(),
//                therapySessionDto.getSessionName(),
//                therapySessionDto.getDate(),
//                therapySessionDto.getTime(),
//                patient
//        );
//
//       return therapySessionDao.update(therapySession);
//    }
        return false;
    }

    @Override
    public boolean delete(String pk){
        return therapySessionDao.delete(pk);
    }
}
