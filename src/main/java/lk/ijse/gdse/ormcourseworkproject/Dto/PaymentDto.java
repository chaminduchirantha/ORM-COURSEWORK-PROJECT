package lk.ijse.gdse.ormcourseworkproject.Dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentDto {
    private String paymentId;
    private Date paymentDate;
    private String paymentType;
    private double paymentAmount;
    private String patientId;
}
