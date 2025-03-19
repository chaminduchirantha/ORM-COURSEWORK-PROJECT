package lk.ijse.gdse.ormcourseworkproject.Entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "therapyProgramme")
public class TherapyProgramme{
    @Id
    @Column(name = "therapyProgrammeId")
    private String therapyProgrammeId;
    private String therapyProgrammeName;
    private String therapyDuration;
    private double therapyPrice;

    @ManyToOne
    @JoinColumn(name = "therapistId")
    private Therapist therapist;
}
