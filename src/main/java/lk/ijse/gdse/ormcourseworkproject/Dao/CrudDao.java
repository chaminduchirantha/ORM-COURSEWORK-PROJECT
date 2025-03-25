package lk.ijse.gdse.ormcourseworkproject.Dao;

import lk.ijse.gdse.ormcourseworkproject.Entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CrudDao <T>extends SuperDao{
    public String getNextId() throws SQLException, IOException;
    public List<T> getAll() throws SQLException, IOException ;
    public void save(T entity) ;
    public void update(T entity) ;
    public void delete(String pk) ;
}
