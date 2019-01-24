package ua.logos.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor


@Entity
@Table(name = "userDetails")
public class UserDetailsEntity  extends  BaseEntity{

    @Column(name = "email",nullable = false)
    private  String email;

    @Column(name = "birth_date",nullable = false)
    private  String birthDate;

    @Column(name = "marital_status",nullable = false)
    private  String maritalStatus;

    @Column(name = "hobby",columnDefinition = "Text")
    private  String hobby;


    @Column(name = "technologies_experience",columnDefinition = "Text")
    private  String technologiesExperience;



    @OneToOne
    @JoinColumn(name = "user_id")
    private  UserEntity user;

}
