package ua.logos.domaim;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.logos.entity.UserEntity;

@Getter
@Setter
@NoArgsConstructor

public class UserDetailsDTO {


    private  Long id;
    private  String email;
    private  String birthDate;
    private  String maritalStatus;
    private  String hobby;
    private  String technologiesExperience;
    private UserEntity user;

}
