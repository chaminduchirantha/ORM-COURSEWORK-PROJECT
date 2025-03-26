package lk.ijse.gdse.ormcourseworkproject.Dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CrudDao <T>extends SuperDao{
    public String getNextId() throws SQLException, IOException;
    public List<T> getAll() throws SQLException, IOException ;
    public boolean save(T entity) ;
    public boolean update(T entity) ;
    public boolean delete(String pk) ;
}
