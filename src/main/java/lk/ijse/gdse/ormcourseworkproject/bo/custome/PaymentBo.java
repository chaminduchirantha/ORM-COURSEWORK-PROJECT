package lk.ijse.gdse.ormcourseworkproject.bo.custome;

import lk.ijse.gdse.ormcourseworkproject.Dto.PaymentDto;
import lk.ijse.gdse.ormcourseworkproject.Entity.Patient;
import lk.ijse.gdse.ormcourseworkproject.Entity.Payment;
import lk.ijse.gdse.ormcourseworkproject.bo.SuperBo;
import lk.ijse.gdse.ormcourseworkproject.config.FactoryConfiguration;
import org.hibernate.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentBo extends SuperBo {
    public String getNextId() throws SQLException, IOException ;
    public List<PaymentDto> getAll() throws SQLException, IOException ;
    public boolean save(PaymentDto paymentDto) ;
    public boolean update(PaymentDto paymentDto) ;
    public boolean delete(String pk);
}
