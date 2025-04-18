package lk.ijse.gdse.ormcourseworkproject.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientDto {
    private String patientID;
    private String patientName;
    private String gender;
    private LocalDate birthDate;
    private String address;
    private String phone;
    private String medicalHistory;
}
