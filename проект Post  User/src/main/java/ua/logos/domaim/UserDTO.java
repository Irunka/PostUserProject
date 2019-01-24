package ua.logos.domaim;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor


public class UserDTO {

    private  Long id;
    private  String firstName;
    private  String lastName;
    private  String nickname;
    private  String firstDate;
}
