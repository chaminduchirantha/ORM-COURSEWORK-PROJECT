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
    private String paymentMethod;
    private double cashPrice;
    private double balance;
    private double totalAmount;
    private String patientId;
}
