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
public class Payment {
    @Id
    private String paymentId;
    private Date paymentDate;
    private String paymentType;
    private double paymentAmount;


    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

}
