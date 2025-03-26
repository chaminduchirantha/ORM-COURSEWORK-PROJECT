package lk.ijse.gdse.ormcourseworkproject.bo.custome.impl;

import lk.ijse.gdse.ormcourseworkproject.Dao.custome.TherapyProgrammeDao;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.impl.TherapyProgrammeDaoImpl;
import lk.ijse.gdse.ormcourseworkproject.Dto.TherapistDto;
import lk.ijse.gdse.ormcourseworkproject.Dto.TherapyProgrammeDto;
import lk.ijse.gdse.ormcourseworkproject.Entity.TherapyProgramme;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.TherapyProgrammeBo;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapyProgrammeBoImpl implements TherapyProgrammeBo {
    TherapyProgrammeDao therapyProgrammeDao = new TherapyProgrammeDaoImpl();

    @Override
    public String getNextId() throws SQLException, IOException {
        return therapyProgrammeDao.getNextId();

    }

    @Override
    public List<TherapyProgramme> getAll(){
        List<TherapyProgramme>therapyProgrammes = new ArrayList<TherapyProgramme>();
        List<TherapyProgrammeDto>therapyProgrammeDtos=new ArrayList<>();
        for (TherapyProgramme therapyProgramme : therapyProgrammes){
            TherapyProgrammeDto therapyProgrammeDto = new TherapyProgrammeDto();
            therapyProgrammeDto.setTherapyProgrammeId(therapyProgramme.getTherapyProgrammeId());
            therapyProgrammeDto.setTherapyProgrammeName(therapyProgramme.getTherapyProgrammeName());
            therapyProgrammeDto.setTherapyDuration(therapyProgramme.getTherapyDuration());
            therapyProgrammeDto.setTherapyPrice(therapyProgramme.getTherapyPrice());
            therapyProgrammeDtos.add(therapyProgrammeDto);
        }
        return therapyProgrammes;

    }

    @Override
    public boolean save(TherapyProgrammeDto therapyProgrammeDto){
        return therapyProgrammeDao.save(new TherapyProgramme(therapyProgrammeDto.getTherapyProgrammeId(),therapyProgrammeDto.getTherapyProgrammeName(),therapyProgrammeDto.getTherapyDuration(),therapyProgrammeDto.getTherapyPrice()));

    }

    @Override
    public boolean update(TherapyProgrammeDto therapyProgrammeDto){
        return therapyProgrammeDao.update(new TherapyProgramme(therapyProgrammeDto.getTherapyProgrammeId(),therapyProgrammeDto.getTherapyProgrammeName(),therapyProgrammeDto.getTherapyDuration(),therapyProgrammeDto.getTherapyPrice()));
    }

    @Override
    public boolean delete(String pk){
        return therapyProgrammeDao.delete(pk);
    }
}
