package lk.ijse.gdse.ormcourseworkproject.Dto.Tm;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapySessionTm {
    private String therapySessionId;
    private Date date;
    private String time;
    private String sessionName;
    private String patientId;
    private String therapyProgrammeId;
}
