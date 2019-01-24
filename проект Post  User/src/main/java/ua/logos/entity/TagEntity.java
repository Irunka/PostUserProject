package ua.logos.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor


@Entity
@Table(name = "tags")
public class TagEntity  extends  BaseEntity{

    @Column(name = "name")
    private  String name;

}
