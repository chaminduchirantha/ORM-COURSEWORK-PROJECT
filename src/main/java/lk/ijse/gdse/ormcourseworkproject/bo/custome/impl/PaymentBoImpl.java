package lk.ijse.gdse.ormcourseworkproject.bo.custome.impl;

import lk.ijse.gdse.ormcourseworkproject.Dao.custome.PaymentDao;
import lk.ijse.gdse.ormcourseworkproject.Dao.custome.impl.PaymentDaoImpl;
import lk.ijse.gdse.ormcourseworkproject.Dto.PaymentDto;
import lk.ijse.gdse.ormcourseworkproject.Entity.Patient;
import lk.ijse.gdse.ormcourseworkproject.Entity.Payment;
import lk.ijse.gdse.ormcourseworkproject.bo.custome.TherapySessionBo;
import lk.ijse.gdse.ormcourseworkproject.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBoImpl {
    PaymentDao paymentDao = new PaymentDaoImpl();
    TherapySessionBo therapySessionBo = new TherapySessionBoImpl();

    public String getNextId() throws SQLException, IOException {
        return paymentDao.getNextId();
    }


    public List<Payment> getAll() throws SQLException, IOException {
//        List<Payment> payments = paymentDao.getAll();
//        List<PaymentDto> paymentDtos = new ArrayList<>();
//        for (Payment payment : payments) {
//            PaymentDto paymentDto = new PaymentDto();
//            paymentDto.setPaymentId(payment.getPaymentId());
//            paymentDto.setPaymentMethod(payment.getPaymentMethod());
//            paymentDto.setBalance(payment.getBalance());
//            paymentDto.setTotalAmount(payment.setTotalAmount());
//            paymentDto.setPatientId(String.valueOf(payment.getPatient()));
//            paymentDtos.add(paymentDto);
//
//        }
//        return payments;
        return List.of();
    }

    public boolean save(PaymentDto paymentDto) {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        try{
//            Patient patient = session.get(Patient.class, paymentDto.getPatientId());
//            if (patient == null) {
//                return false;
//            }
//            Payment payment = new Payment(
//                    paymentDto.getPaymentId(),
//                    paymentDto.getPaymentDate(),
//                    paymentDto.getPaymentType(),
//                    paymentDto.getPaymentAmount(),
//                    paymentDto.getPatientId()
//            );
//            session.persist(payment);
//
//            boolean isStatusUpdated = therapySessionBo.updateStatus(paymentDto.getPaymentId());
//            if (!isStatusUpdated) {
//                transaction.rollback();
//                return false;
//            }
//
//            transaction.commit();
//            return true;
//
//        }catch (Exception e){
//            transaction.rollback();
//            e.printStackTrace();
//            return false;
//        }finally {
//            session.close();
//        }
        return false;
    }


    public boolean update(PaymentDto paymentDto) {
//        return paymentDao.save(new Payment(paymentDto.getPaymentId(),paymentDto.getPaymentDate(),paymentDto.getPaymentType(),paymentDto.getPaymentAmount(),paymentDto.getPatientId()));
        return false;
    }

    public boolean delete(String pk){
        return paymentDao.delete(pk);
    }
}
