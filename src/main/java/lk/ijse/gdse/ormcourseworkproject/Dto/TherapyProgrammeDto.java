package lk.ijse.gdse.ormcourseworkproject.Dto;



import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class TherapyProgrammeDto {

    private String therapyProgrammeId;
    private String therapyProgrammeName;
    private String therapyDuration;
    private double therapyPrice;


}
