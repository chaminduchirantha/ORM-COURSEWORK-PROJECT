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

    private Date date;
    private String time;
    private String sessionName;

    @ManyToOne
    @JoinColumn(name = "therapyProgrammeId")
    private TherapyProgramme therapyProgramme;

    public TherapySession(String therapySessionId, String sessionName, Date date, String time, Patient patient, TherapyProgramme therapist) {
    }
}
