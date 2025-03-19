package lk.ijse.gdse.ormcourseworkproject.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "therapy_session")
public class TherapySession {
    @Id
    @Column(name = "sessionId")
    private String sessionID;
    private String sessionName;
    private String sessionDate;
    private String sessionTime;


    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    @OneToMany(mappedBy = "therapySession")
    private List<Appointment> appointments;
}
