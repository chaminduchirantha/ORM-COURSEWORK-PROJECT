package lk.ijse.gdse.ormcourseworkproject.bo.custome.impl;

import lk.ijse.gdse.ormcourseworkproject.Dao.custome.TherapistDao;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.impl.TherapistDaoImpl;
import lk.ijse.gdse.ormcourseworkproject.Dto.TherapistDto;
import lk.ijse.gdse.ormcourseworkproject.Entity.Therapist;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.TherapistBo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapistBoImpl implements TherapistBo {

    TherapistDao therapistDao = new TherapistDaoImpl();

    @Override
    public String getNextId() throws SQLException, IOException {
        return therapistDao.getNextId();
    }

    @Override
    public TherapistDto findBy(String therapistId) throws SQLException, ClassNotFoundException {
        Therapist therapist = therapistDao.findBy(therapistId);
        return new TherapistDto(therapist.getTherapistId(),therapist.getTherapistName(),therapist.getTherapistAddress(),therapist.getAge(),therapist.getTherapistPhone());
    }

    @Override
    public List<TherapistDto> getAll(){
        List<Therapist>therapists =new ArrayList<>();
        List<TherapistDto>therapistDtos = new ArrayList<>();

        for (Therapist therapist : therapists) {
            TherapistDto therapistDto=new TherapistDto();
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
        return therapistDao.save(new Therapist(therapistDto.getTherapistId(),therapistDto.getTherapistName(),therapistDto.getTherapistAddress(),therapistDto.getAge(),therapistDto.getTherapistPhone()));
    }

    @Override
    public boolean update(TherapistDto therapistDto) {
        return therapistDao.update(new Therapist(therapistDto.getTherapistId(),therapistDto.getTherapistName(),therapistDto.getTherapistAddress(),therapistDto.getAge(),therapistDto.getTherapistPhone()));
    }

    @Override
    public boolean delete(String pk) {
        return therapistDao.delete(pk);
    }
}
