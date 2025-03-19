package lk.ijse.gdse.ormcourseworkproject.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @Column(name = "patientId")
    private String patientID;
    private String patientName;
    private String gender;
    private int age;
    private Date birthDate;
    private String address;
    private String phone;

    @OneToMany
    @JoinColumn(name = "paymentId")
    private List<Payment> payments;

    @OneToMany(mappedBy = "patient")
    private List <Appointment>appointments;
}
