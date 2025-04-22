package lk.ijse.gdse.ormcourseworkproject.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "payment")
public class Payment implements SuperEntity {
    @Id
    private String paymentId;
    private String paymentMethod;
    private double cashPrice;
    private double balance;
    private double totalAmount;



    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    public Payment(String paymentId, Date paymentDate, String paymentType, double paymentAmount, String patientId) {
    }
}
