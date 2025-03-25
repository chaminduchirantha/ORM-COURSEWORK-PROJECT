package lk.ijse.gdse.ormcourseworkproject.Entity;

import jakarta.persistence.*;
import lombok.Data;


@Data

@Entity
@Table(name = "registration")
public class Registration implements SuperEntity {
    @Id
    private String registrationId;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne
//    @MapsId("therapyProgrammeId")
    @JoinColumn(name ="therapyProgrammeId")
    private TherapyProgramme therapyProgramme;

    private String registrationDate;

}
