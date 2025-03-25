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
@Table(name = "therapy_session")
public class TherapySession implements SuperEntity {
    @Id
    @Column(name = "sessionId")
    private String sessionID;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;


    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    private Date date;
    private String time;
    private String sessionName;

}
