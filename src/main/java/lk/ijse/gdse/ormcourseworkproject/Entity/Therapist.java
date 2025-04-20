package lk.ijse.gdse.ormcourseworkproject.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table( name = "therapist")
public class Therapist implements SuperEntity {
    @Id
    @Column(name = "therapistId")
    private String therapistId;
    private String therapistName;
    private String therapistAddress;
    private int age;
    private String therapistPhone;

    @ManyToOne
    @JoinColumn(name = "therapyProgrammeId")
    private TherapyProgramme therapyProgramme;

    public Therapist(String therapistId, String therapistName, String therapistAddress, int age, String therapistPhone, String therapistProgrammeId) {
    }
}
