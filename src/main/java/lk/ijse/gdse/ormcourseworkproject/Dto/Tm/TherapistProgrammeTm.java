package lk.ijse.gdse.ormcourseworkproject.Dto.Tm;


import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapistProgrammeTm {
    private String therapyProgrammeId;
    private String therapyProgrammeName;
    private String therapyDuration;
    private double therapyPrice;
}
