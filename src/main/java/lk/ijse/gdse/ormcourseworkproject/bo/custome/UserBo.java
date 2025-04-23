package lk.ijse.gdse.ormcourseworkproject.bo.custome;

import lk.ijse.gdse.ormcourseworkproject.Dto.UserDto;
import lk.ijse.gdse.ormcourseworkproject.Entity.User;
import lk.ijse.gdse.ormcourseworkproject.bo.SuperBo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserBo extends SuperBo {
    public String getNextId() throws SQLException, IOException ;
    public List<UserDto> getAll() throws SQLException, IOException ;
    public boolean save(UserDto userDto) ;
    public boolean update(UserDto userDto) ;
    public boolean delete(String pk) ;
    public User getUsers(String role) ;
    public UserDto getUser(String ids) ;
    public String getuserId(String username) ;
    public boolean changePassword(String username, String newPassword) ;
    public boolean updatePassword(String username, String newPassword);
}
