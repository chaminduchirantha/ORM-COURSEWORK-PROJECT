package lk.ijse.gdse.ormcourseworkproject.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
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
    private String patientID;
    private String patientName;
    private String gender;
    private LocalDate birthDate;
    private String medicalHistory;
    private String address;
    private String phone;


    @OneToMany(mappedBy = "patient")
    private List<TherapySession> therapySessions;

    @OneToMany(mappedBy = "patient")
    private List<Payment> payments;

    public Patient(String patientID, String patientName, String gender, LocalDate birthDate, String address, String phone, String medicalHistory) {
    }
}
