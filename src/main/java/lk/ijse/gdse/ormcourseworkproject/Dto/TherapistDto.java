package lk.ijse.gdse.ormcourseworkproject.Dto;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TherapistDto {
    private String therapistId;
    private String therapistName;
    private String therapistAddress;
    private int age;
    private String therapistPhone;
    private String therapistProgrammeId;

    public TherapistDto(String therapistId, String therapistName, String therapistAddress, int age, String therapistPhone) {
    }
}
