package lk.ijse.gdse.ormcourseworkproject.Dto;

import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapySessionDto {
    private String therapySessionId;
    private Date date;
    private String time;
    private String sessionName;
    private String patientId;
    private String therapistId;
}
