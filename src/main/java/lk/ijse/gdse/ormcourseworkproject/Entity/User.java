package lk.ijse.gdse.ormcourseworkproject.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.extern.apachecommons.CommonsLog;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "userId")
    private String usernameId;
    private String userName;
    private String userPassword;
    private String userRole;
}
