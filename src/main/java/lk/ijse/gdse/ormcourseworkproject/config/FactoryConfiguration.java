package lk.ijse.gdse.ormcourseworkproject.config;

import lk.ijse.gdse.ormcourseworkproject.Entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Therapist.class).addAnnotatedClass(TherapySession.class).addAnnotatedClass(Payment.class) .addAnnotatedClass(Patient.class).addAnnotatedClass(TherapyProgramme.class).addAnnotatedClass(Appointment.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        if (factoryConfiguration == null) {
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}


