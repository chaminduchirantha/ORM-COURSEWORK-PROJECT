package lk.ijse.gdse.ormcourseworkproject.Dto.Tm;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapistTm {
    private String therapistId;
    private String therapistName;
    private int age;
    private String therapistPhone;
    private String therapistAddress;
    private String therapistProgrammeId;
}
