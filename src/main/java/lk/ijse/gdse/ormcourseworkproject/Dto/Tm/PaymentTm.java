package lk.ijse.gdse.ormcourseworkproject.Dto.Tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentTm {
    private String paymentId;
    private String paymentMethod;
    private double cashPrice;
    private double balance;
    private double totalAmount;
    private String patientId;
}
