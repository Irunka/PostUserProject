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
@Table(name = "comments")
public class CommentEntity extends  BaseEntity {


    @Column(name = "description_comment",columnDefinition = "Text")
    private  String descriptionComment;


}

