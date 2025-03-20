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
public class Therapist {
    @Id
    @Column(name = "therapistId")
    private String therapistId;
    private String therapistName;
    private String therapistAddress;
    private int age;
    private String therapistPhone;

    @OneToMany
    @JoinColumn(name = "therapyProgrammeId")
    private List<TherapyProgramme> therapyProgrammes;

}
