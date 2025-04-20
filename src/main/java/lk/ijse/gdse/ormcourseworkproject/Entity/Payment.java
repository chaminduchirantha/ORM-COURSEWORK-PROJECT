package lk.ijse.gdse.ormcourseworkproject.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
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
    private Date paymentDate;
    private String paymentType;
    private double paymentAmount;


    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    public Payment(String paymentId, Date paymentDate, String paymentType, double paymentAmount, String patientId) {
    }
}
