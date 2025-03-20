package lk.ijse.gdse.ormcourseworkproject;

import lk.ijse.gdse.ormcourseworkproject.Entity.Payment;
import lk.ijse.gdse.ormcourseworkproject.Entity.Therapist;
import lk.ijse.gdse.ormcourseworkproject.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        Therapist therapist = new Therapist();
        therapist.setTherapistId("2");
        therapist.setAge(25);
        therapist.setTherapistPhone("077567453");
        therapist.setTherapistAddress("gall");

        transaction.commit();
        session.save(therapist);
        session.close();
    }
}
