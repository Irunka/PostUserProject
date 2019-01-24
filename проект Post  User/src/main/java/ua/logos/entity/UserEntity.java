package ua.logos.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor


@Entity
@Table(name = "users")
public class UserEntity  extends  BaseEntity{


    @Column(name = "first_name",nullable = false)
    private  String firstName;

    @Column(name = "last_name",nullable = false)
    private  String lastName;

    @Column(name = "nickname",nullable = false)
    private  String nickname;

    @Column(name = "first_date",nullable = false)
    private  String firstDate;





}
