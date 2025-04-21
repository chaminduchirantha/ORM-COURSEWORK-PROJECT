package lk.ijse.gdse.ormcourseworkproject.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientDto {
    private String patientId;
    private String name;
    private Date birthday;
    private String contactNumber;
    private String medicalHistory;

}
