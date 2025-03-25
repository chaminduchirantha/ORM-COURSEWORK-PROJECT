package lk.ijse.gdse.ormcourseworkproject.Dao.custome;

import lk.ijse.gdse.ormcourseworkproject.Dao.CrudDao;
import lk.ijse.gdse.ormcourseworkproject.Entity.User;
import lk.ijse.gdse.ormcourseworkproject.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserDao extends CrudDao<User> {

}
