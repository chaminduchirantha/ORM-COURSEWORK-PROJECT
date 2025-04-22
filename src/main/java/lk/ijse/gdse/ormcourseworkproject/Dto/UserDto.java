package lk.ijse.gdse.ormcourseworkproject.Dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userNameId;
    private String userName;
    private String userPassword;
    private String userRole;
}
