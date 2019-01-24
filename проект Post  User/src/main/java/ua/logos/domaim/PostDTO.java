package ua.logos.domaim;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.logos.entity.UserEntity;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class PostDTO {


    private  Long id;
    private  String title;
    private  String description;
    private  String firstDate;

    private UserDTO user;

    private List<CommentDTO> comments;

    private  List<TagDTO>tags;
}
