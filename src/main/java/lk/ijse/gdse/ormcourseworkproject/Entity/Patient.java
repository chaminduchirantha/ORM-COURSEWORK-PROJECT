package lk.ijse.gdse.ormcourseworkproject.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "patient")
public class Patient implements SuperEntity {
    @Id
    @Column(name = "patientId")
    private String patientId;
    private String name;
    private Date birthday;
    private String contactNumber;
    private String medicalHistory;


    @OneToMany(mappedBy = "patient")
    private List<TherapySession> therapySessions;

    @OneToMany(mappedBy = "patient")
    private List<Payment> payments;

    public Patient(String patientId, String name, Date birthday, String contactNumber, String medicalHistory) {
        this.patientId = patientId;
        this.name = name;
        this.birthday = birthday;
        this.contactNumber = contactNumber;
        this.medicalHistory = medicalHistory;
    }
}
