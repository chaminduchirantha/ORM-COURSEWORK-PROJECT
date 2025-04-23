package lk.ijse.gdse.ormcourseworkproject.bo.custome.impl;

import lk.ijse.gdse.ormcourseworkproject.Dao.DaoFactory;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.TherapyProgrammeDao;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.impl.TherapyProgrammeDaoImpl;
import lk.ijse.gdse.ormcourseworkproject.Dto.TherapyProgrammeDto;
import lk.ijse.gdse.ormcourseworkproject.Entity.TherapyProgramme;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.TherapyProgrammeBo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapyProgrammeBoImpl implements TherapyProgrammeBo {
    TherapyProgrammeDao therapyProgrammeDao = (TherapyProgrammeDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.THERAPYPROGRAMME);

    @Override
    public String getNextId() throws SQLException, IOException {
        return therapyProgrammeDao.getNextId();

    }

    @Override
    public List<TherapyProgrammeDto> getAll() throws SQLException, IOException {
        List<TherapyProgramme>therapyProgrammes= therapyProgrammeDao.getAll();
        List<TherapyProgrammeDto>therapyProgrammeDtos=new ArrayList<>();

        for (TherapyProgramme therapyProgramme : therapyProgrammes){
            TherapyProgrammeDto therapyProgrammeDto = new TherapyProgrammeDto();
            therapyProgrammeDto.setTherapyProgrammeId(therapyProgramme.getTherapyProgrammeId());
            therapyProgrammeDto.setTherapyProgrammeName(therapyProgramme.getTherapyProgrammeName());
            therapyProgrammeDto.setTherapyDuration(therapyProgramme.getTherapyDuration());
            therapyProgrammeDto.setTherapyPrice(therapyProgramme.getTherapyPrice());
            therapyProgrammeDtos.add(therapyProgrammeDto);
        }
        return therapyProgrammeDtos;

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
    public ArrayList<String> getAllTherapyProgrammeId() throws SQLException, ClassNotFoundException, IOException {
        ArrayList<String> allIds = new ArrayList<>();
        ArrayList<String>all = therapyProgrammeDao.getAllTherapyProgrammeId();
        for(String id: all){
            allIds.add(id);
        }
        return allIds;
    }

    @Override
    public TherapyProgrammeDto findBy(String therapyProgrammeId) throws SQLException, ClassNotFoundException {
        TherapyProgramme therapyProgramme = therapyProgrammeDao.findBy(therapyProgrammeId);
        return new TherapyProgrammeDto(therapyProgramme.getTherapyProgrammeId(), therapyProgramme.getTherapyProgrammeName(), therapyProgramme.getTherapyDuration(), therapyProgramme.getTherapyPrice());
    }

    @Override
    public boolean delete(String pk){
        return therapyProgrammeDao.delete(pk);
    }
}
