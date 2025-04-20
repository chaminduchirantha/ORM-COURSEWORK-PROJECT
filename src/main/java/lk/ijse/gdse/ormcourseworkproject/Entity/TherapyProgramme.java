package lk.ijse.gdse.ormcourseworkproject.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "therapyProgramme")
public class TherapyProgramme implements SuperEntity {
    @Id
    @Column(name = "therapyProgrammeId")
    private String therapyProgrammeId;
    private String therapyProgrammeName;
    private String therapyDuration;
    private double therapyPrice;


//
//    @OneToMany(mappedBy = "therapyProgramme")
//    private List<Therapist>therapists;

//    public TherapyProgramme(String therapyProgrammeId, String therapyProgrammeName, String therapyDuration, double therapyPrice) {
//    }
}
