package lk.ijse.gdse.ormcourseworkproject.bo.custome.impl;

import lk.ijse.gdse.ormcourseworkproject.Dao.DaoFactory;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.TherapistDao;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.impl.TherapistDaoImpl;
import lk.ijse.gdse.ormcourseworkproject.Dto.TherapistDto;
import lk.ijse.gdse.ormcourseworkproject.Entity.Patient;
import lk.ijse.gdse.ormcourseworkproject.Entity.Payment;
import lk.ijse.gdse.ormcourseworkproject.Entity.Therapist;
import lk.ijse.gdse.ormcourseworkproject.Entity.TherapyProgramme;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.TherapistBo;
import lk.ijse.gdse.ormcourseworkproject.config.FactoryConfiguration;
import org.hibernate.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapistBoImpl implements TherapistBo {

    TherapistDao therapistDao = (TherapistDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.THERAPIST);

    @Override
    public String getNextId() throws SQLException, IOException {
        return therapistDao.getNextId();
    }

    @Override
    public TherapistDto findBy(String therapistId) throws SQLException, ClassNotFoundException {
        Therapist therapist = therapistDao.findBy(therapistId);
        return new TherapistDto(therapist.getTherapistId(),therapist.getTherapistName(),therapist.getTherapistAddress(),therapist.getAge(),therapist.getTherapistPhone()!= null ? therapist.getTherapyProgramme().getTherapyProgrammeId():null);
    }

    @Override
    public List<TherapistDto> getAll() throws SQLException, IOException {
        List<Therapist>therapists = therapistDao.getAll();
        List<TherapistDto>therapistDtos = new ArrayList<>();

        for (Therapist therapist : therapists) {
            TherapistDto therapistDto=new TherapistDto();
            therapistDto.setTherapistId(therapist.getTherapistId());
            if (therapist.getTherapyProgramme() != null) {
                therapistDto.setTherapistProgrammeId(therapist.getTherapyProgramme().getTherapyProgrammeId());
            }else {
                therapistDto.setTherapistProgrammeId("N/A");
            }
            therapistDto.setTherapistId(therapist.getTherapistId());
            therapistDto.setTherapistName(therapist.getTherapistName());
            therapistDto.setTherapistAddress(therapist.getTherapistAddress());
            therapistDto.setAge(therapist.getAge());
            therapistDto.setTherapistPhone(therapist.getTherapistPhone());

            therapistDtos.add(therapistDto);
        }
        return therapistDtos;
    }

    @Override
    public boolean save(TherapistDto therapistDto) {
        Session session = FactoryConfiguration.getInstance().getSession();

        TherapyProgramme therapyProgramme = session.get(TherapyProgramme.class, therapistDto.getTherapistProgrammeId());
        if (therapyProgramme == null) {
            return false;
        }
        Therapist therapist = new Therapist(
                therapistDto.getTherapistId(),
                therapistDto.getTherapistName(),
                therapistDto.getTherapistAddress(),
                therapistDto.getAge(),
                therapistDto.getTherapistPhone(),
                therapyProgramme

        );
        return therapistDao.save(therapist);
    }

    @Override
    public boolean update(TherapistDto therapistDto) {
        Session session = FactoryConfiguration.getInstance().getSession();

        TherapyProgramme therapyProgramme = session.get(TherapyProgramme.class, therapistDto.getTherapistProgrammeId());
        if (therapyProgramme == null) {
            return false;
        }
        Therapist therapist = new Therapist(
                therapistDto.getTherapistId(),
                therapistDto.getTherapistName(),
                therapistDto.getTherapistAddress(),
                therapistDto.getAge(),
                therapistDto.getTherapistPhone(),
                therapyProgramme

        );
        return therapistDao.update(therapist);
    }

    @Override
    public boolean delete(String pk) {
        return therapistDao.delete(pk);
    }
}
