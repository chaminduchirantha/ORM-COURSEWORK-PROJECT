module lk.ijse.gdse.ormcourseworkproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jakarta.persistence;
    requires lombok;
    requires java.sql;
    requires net.sf.jasperreports.core;

    requires org.hibernate.orm.core;
    requires java.naming;

    opens lk.ijse.gdse.ormcourseworkproject.Entity to org.hibernate.orm.core;
    opens lk.ijse.gdse.ormcourseworkproject.config to jakarta.persistence;
    opens lk.ijse.gdse.ormcourseworkproject.controller to javafx.fxml;
    exports lk.ijse.gdse.ormcourseworkproject;
}