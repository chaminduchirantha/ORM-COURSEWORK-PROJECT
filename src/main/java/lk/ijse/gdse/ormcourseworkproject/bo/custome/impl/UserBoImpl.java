package lk.ijse.gdse.ormcourseworkproject.bo.custome.impl;

import lk.ijse.gdse.ormcourseworkproject.Dao.custome.UserDao;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.impl.UserDaoImpl;
import lk.ijse.gdse.ormcourseworkproject.Dto.UserDto;
import lk.ijse.gdse.ormcourseworkproject.Entity.User;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.UserBo;
import lk.ijse.gdse.ormcourseworkproject.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBo {
    UserDao userDao = new UserDaoImpl();

    @Override
    public String getNextId() throws SQLException, IOException {
        return userDao.getNextId();
    }


    @Override
    public List<UserDto> getAll() throws SQLException, IOException {
        List<User>users = userDao.getAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto();
            userDto.setUserNameId(user.getUsernameId());
            userDto.setUserName(user.getUserName());
            userDto.setUserPassword(user.getUserPassword());
            userDto.setUserRole(user.getUserRole());
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public boolean save(UserDto userDto) {
        return userDao.save(new User(userDto.getUserNameId(),userDto.getUserName(),userDto.getUserPassword(),userDto.getUserRole()));
    }

    @Override
    public boolean update(UserDto userDto) {
        return userDao.update(new User(userDto.getUserNameId(),userDto.getUserName(),userDto.getUserPassword(),userDto.getUserRole()));
    }

    @Override
    public boolean delete(String pk) {
        return userDao.delete(pk);
    }
}
