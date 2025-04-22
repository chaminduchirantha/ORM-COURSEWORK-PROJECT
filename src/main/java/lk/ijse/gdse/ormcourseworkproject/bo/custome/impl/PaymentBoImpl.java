package lk.ijse.gdse.ormcourseworkproject.bo.custome.impl;

import lk.ijse.gdse.ormcourseworkproject.Dao.custome.PaymentDao;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.impl.PaymentDaoImpl;
import lk.ijse.gdse.ormcourseworkproject.Dto.PaymentDto;
import lk.ijse.gdse.ormcourseworkproject.Entity.Patient;
import lk.ijse.gdse.ormcourseworkproject.Entity.Payment;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.PaymentBo;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.TherapySessionBo;
import lk.ijse.gdse.ormcourseworkproject.config.FactoryConfiguration;
import org.hibernate.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBoImpl implements PaymentBo {
    PaymentDao paymentDao = new PaymentDaoImpl();

    public String getNextId() throws SQLException, IOException {
        return paymentDao.getNextId();
    }


    public List<PaymentDto> getAll() throws SQLException, IOException {
     List<Payment> payments = paymentDao.getAll();
        List<PaymentDto> paymentDTOS = new ArrayList<>();

        for (Payment payment : payments) {
            PaymentDto paymentDTO = new PaymentDto();
            paymentDTO.setPaymentId(payment.getPaymentId());
            if (payment.getPatient() != null) {
                paymentDTO.setPatientId(payment.getPatient().getPatientId());
            }else {
                paymentDTO.setPatientId("N/A");
            }
            paymentDTO.setPaymentMethod(payment.getPaymentMethod());
            paymentDTO.setTotalAmount(payment.getTotalAmount());
            paymentDTO.setCashPrice(payment.getCashPrice());
            paymentDTO.setBalance(payment.getBalance());
            paymentDTOS.add(paymentDTO);
        }
        return paymentDTOS;
    }

    public boolean save(PaymentDto paymentDto) {
        Session session = FactoryConfiguration.getInstance().getSession();

        Patient patient = session.get(Patient.class, paymentDto.getPatientId());
        if (patient == null) {
            return false;
        }
        Payment payment = new Payment(
                paymentDto.getPaymentId(),
                paymentDto.getPaymentMethod(),
                paymentDto.getCashPrice(),
                paymentDto.getBalance(),
                paymentDto.getTotalAmount(),
                patient

        );
        return paymentDao.save(payment);
    }

    @Override
    public boolean update(PaymentDto paymentDto) {
        return false;
    }

    public boolean delete(String pk){
        return paymentDao.delete(pk);
    }
}
