package lk.ijse.gdse.ormcourseworkproject.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
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
    private Date birthDate;
    private String address;
    private String phone;

}
