package lk.ijse.gdse.ormcourseworkproject.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


@Entity

public class Appointment {
    @Id
    @Column(name = "appointmentId")
    private String appointmentID;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "sessionID")
    private TherapySession therapySession;

    private Date appointmentDate;
    private String appointmentTime;


}
