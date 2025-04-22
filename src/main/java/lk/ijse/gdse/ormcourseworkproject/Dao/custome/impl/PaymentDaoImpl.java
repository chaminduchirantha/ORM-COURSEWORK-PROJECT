package lk.ijse.gdse.ormcourseworkproject.Dao.custome.impl;

import lk.ijse.gdse.ormcourseworkproject.Dao.custome.PaymentDao;
import lk.ijse.gdse.ormcourseworkproject.Entity.Payment;
import lk.ijse.gdse.ormcourseworkproject.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {

    @Override
    public String getNextId() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        String hql = "SELECT m.paymentId FROM Payment m ORDER BY m.paymentId DESC";
        Query<String> query = session.createQuery(hql);

        query.setMaxResults(1);
        String lastId = query.uniqueResult();

        transaction.commit();
        session.close();

        if (lastId != null) {
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("M%03d", newIdIndex);
        }
        return "M001";
    }

    @Override
    public List<Payment> getAll(){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        Query<Payment> query = session.createQuery("SELECT m from Payment m", Payment.class);
        List<Payment> payments = query.list();
        transaction.commit();
        session.close();
        return payments;
    }


    @Override
    public boolean save(Payment payment) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(payment);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Payment entity) {
        return false;
    }

//    @Override
//    public boolean update(Payment payment) {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        session.merge(payment);
//        transaction.commit();
//        session.close();
//        return true;
//    }

    @Override
    public boolean delete(String pk){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Payment payment = new Payment();
        payment.setPaymentId(pk);
        session.remove(payment);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Payment findBy(String pk) throws SQLException, ClassNotFoundException {
        return null;
    }
}
