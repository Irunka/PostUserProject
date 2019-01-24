package ua.logos.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.logos.domaim.CommentDTO;
import ua.logos.entity.CommentEntity;
import ua.logos.exceptions.NotFoundException;
import ua.logos.repository.CommentRepository;
import ua.logos.service.CommentService;
import ua.logos.utils.ObjectMapperUtils;

import java.util.List;

@Service
public class CommentServiceImpl  implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

@Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public void saveComment(CommentDTO comment) {
        CommentEntity commentEntity=modelMapper.map(comment,CommentEntity.class);
        commentRepository.save(commentEntity);
    }

    @Override
    public List<CommentDTO> findAllComments() {
        List<CommentEntity>comments=commentRepository.findAll();
        List<CommentDTO>commentDTOS=modelMapper.mapAll(comments,CommentDTO.class);

        return commentDTOS;
    }

    @Override
    public CommentDTO findCommentById(Long id) {
        CommentEntity comments=commentRepository.findById(id)
                .orElseThrow(
                        ()-> new NotFoundException("Comment with id ["+ id+"]not found"));

    CommentDTO commentDTO = modelMapper.map(comments,CommentDTO.class);
    return commentDTO;
    }

    @Override
    public CommentDTO updateComment(Long id, CommentDTO commentToUpdate) {
        boolean exists=commentRepository.existsById(id);
        CommentEntity commentFromDB=commentRepository.findById(id)
                .orElseThrow(
                        ()-> new NotFoundException("Comment with id ["+ id+"]not found"));

        commentFromDB.setDescriptionComment(commentToUpdate.getDescriptionComment());
        CommentDTO  commentDTO = modelMapper.map(commentFromDB,CommentDTO.class);
       commentRepository.save(commentFromDB);
       commentDTO.setId(commentFromDB.getId());

       return  commentDTO;

    }
/*private CommentDTO entityToDTOMapper(CommentEntity commentEntity){
        CommentDTO commentDTO=new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setDescriptionComment(commentEntity.getDescriptionComment());

        return  commentDTO;
}
private  CommentEntity dtoToEntityMapper(CommentDTO commentDTO){
        CommentEntity commentEntity= new CommentEntity();
        commentEntity.setId(commentDTO.getId());
        commentEntity.setDescriptionComment(commentDTO.getDescriptionComment());

        return  commentEntity;

}*/



}
