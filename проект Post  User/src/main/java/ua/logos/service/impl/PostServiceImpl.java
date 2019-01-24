package ua.logos.service.impl;

import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.logos.domaim.CommentDTO;
import ua.logos.domaim.PostDTO;
import ua.logos.domaim.TagDTO;
import ua.logos.domaim.UserDTO;
import ua.logos.entity.CommentEntity;
import ua.logos.entity.PostEntity;
import ua.logos.entity.TagEntity;
import ua.logos.entity.UserEntity;
import ua.logos.exceptions.NotFoundException;
import ua.logos.repository.PostRepository;
import ua.logos.repository.UserRepository;
import ua.logos.service.PostService;
import ua.logos.utils.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl  implements PostService {

@Autowired
    private PostRepository postRepository;

@Autowired
private UserRepository userRepository;

    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public void savePost(PostDTO post) {
        PostEntity postEntity = modelMapper.map(post,PostEntity.class);
        postRepository.save(postEntity);
    }

    @Override
    public List<PostDTO> findAllPosts() {
        List<PostEntity>posts=postRepository.findAll();
        List<PostDTO>postDTOS=modelMapper.mapAll(posts,PostDTO.class);
        return postDTOS;
    }

    @Override
    public PostDTO findPostById(Long id) {
        PostEntity posts=postRepository.findById(id)
                .orElseThrow(
                        ()->   new NotFoundException("Post with id ["+ id+"]not found"));
        PostDTO postDTO = modelMapper.map(posts,PostDTO.class);
        return postDTO;
    }

    @Override
    public PostDTO updatePost(Long id ,PostDTO postToUpdate) {
        boolean exists = postRepository.existsById(id);
        PostEntity postFromDB = postRepository.findById(id)
                .orElseThrow(
                        ()-> new NotFoundException("Post with id ["+ id+"]not found"));

        postFromDB.setDescription(postToUpdate.getDescription());
        postFromDB.setFirstDate(postToUpdate.getFirstDate());
        postFromDB.setTitle(postToUpdate.getTitle());


      List<TagDTO>tagDTO = postToUpdate.getTags();
         List<TagDTO>tagToUpdate = new ArrayList<>();
        for (TagDTO tag:tagDTO){
            TagDTO tagDTO1 = new TagDTO();
            tagDTO1.setId(tag.getId());
            tagDTO1.setName(tag.getName());
          tagToUpdate.add(tagDTO1);

        }
        List<CommentDTO>commentDTO = postToUpdate.getComments();
        List<CommentDTO>commentToUpdate = new ArrayList<>();
        for (CommentDTO comment:commentDTO){
            CommentDTO commentDTO1 = new CommentDTO();
            commentDTO1.setId(comment.getId());
            commentDTO1.setDescriptionComment(comment.getDescriptionComment());
            commentToUpdate.add(commentDTO1);
        }

        UserDTO userDTOToUpdate = postToUpdate.getUser();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userDTOToUpdate.getId());
        userDTO.setFirstName(userDTOToUpdate.getFirstName());
        userDTO.setLastName(userDTOToUpdate.getLastName());
        userDTO.setNickname(userDTOToUpdate.getNickname());
        userDTO.setFirstDate(userDTOToUpdate.getFirstDate());


        PostDTO postDTOs= modelMapper.map(postFromDB,PostDTO.class);
        postRepository.save(postFromDB);
        postDTOs.setTags(tagToUpdate);
        postDTOs.setComments(commentToUpdate);
        postDTOs.setUser(userDTOToUpdate);
       postDTOs.setId(postFromDB.getId());

    return postDTOs;








    }




   /* private PostDTO entityToDTOMapper(PostEntity postEntity){
        PostDTO postDTO =new PostDTO();
        postDTO.setId(postEntity.getId());
        postDTO.setDescription(postEntity.getDescription());
        postDTO.setFirstDate(postEntity.getFirstDate());
        postDTO.setTitle(postEntity.getTitle());

        UserEntity user=postEntity.getUser();
        UserDTO userDTO=new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setNickname(user.getNickname());
        userDTO.setFirstDate(user.getFirstDate());


        List<CommentEntity>commentEntities=postEntity.getComments();
        List<CommentDTO>commentDTOS=new ArrayList<>();

        for(CommentEntity commentEntity:commentEntities){
            CommentDTO commentDTO=new CommentDTO();
            commentDTO.setId(commentEntity.getId());
            commentDTO.setDescriptionComment(commentEntity.getDescriptionComment());
            commentDTOS.add(commentDTO);
        }

        List<TagEntity>tagEntities=postEntity.getTags();
        List<TagDTO>tagDTOS=new ArrayList<>();

        for(TagEntity tagEntity:tagEntities){
            TagDTO tagDTO= new TagDTO();
            tagDTO.setId(tagEntity.getId());
            tagDTO.setName(tagEntity.getName());
            tagDTOS.add(tagDTO);
        }


         postDTO.setUser(userDTO);
         postDTO.setComments(commentDTOS);
         postDTO.setTags(tagDTOS);

         return  postDTO;
    }

    private PostEntity dtoToEntityMapper(PostDTO postDTO){
        PostEntity postEntity=new PostEntity();
        postEntity.setId(postDTO.getId());
   postEntity.setTitle(postDTO.getTitle());
   postEntity.setDescription(postDTO.getDescription());
   postEntity.setFirstDate(postDTO.getFirstDate());

     UserDTO userDTO= postDTO.getUser();
   UserEntity userEntity=new UserEntity();
 userEntity.setId(userDTO.getId());
   userEntity.setFirstName(userDTO.getFirstName());
  userEntity.setLastName(userDTO.getLastName());
   userEntity.setNickname(userDTO.getNickname());
   userEntity.setFirstDate(userDTO.getFirstDate());


        List<CommentDTO>commentDTOS=postDTO.getComments();
        List<CommentEntity>commentEntities=new ArrayList<>();

        for(CommentDTO commentDTO:commentDTOS) {
            CommentEntity commentEntity = new CommentEntity();
            commentEntity.setId(commentDTO.getId());
            commentEntity.setDescriptionComment(commentDTO.getDescriptionComment());
            commentEntities.add(commentEntity);
        }

        List<TagDTO>tagDTOS=postDTO.getTags();
        List<TagEntity>tagEntities=new ArrayList<>();

        for (TagDTO tagDTO:tagDTOS){
            TagEntity tagEntity= new TagEntity();
            tagEntity.setId(tagDTO.getId());
            tagEntity.setName(tagDTO.getName());
            tagEntities.add(tagEntity);
        }


        postEntity.setUser(userEntity);
       postEntity.setComments(commentEntities);
        postEntity.setTags(tagEntities);

        return   postEntity;

    }
*/


}

