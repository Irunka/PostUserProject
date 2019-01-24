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
@Table(name = "posts")
public class PostEntity  extends  BaseEntity{

    @Column(name = "title",nullable = false)
    private  String title;

    @Column(name = "description",columnDefinition = "Text")
    private  String description;

    @Column(name = "first_date")
    private  String firstDate;


@ManyToOne
    @JoinColumn(name = "user_id")
    private  UserEntity user;



@ManyToMany
@JoinTable(name = "post_comment",
joinColumns = @JoinColumn(name = "post_id"),
inverseJoinColumns = @JoinColumn(name = "comment_id"))
 private  List<CommentEntity> comments;



  @ManyToMany
    @JoinTable(name = "post_tag",
    joinColumns = @JoinColumn(name = "post_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private  List<TagEntity>tags;

}
