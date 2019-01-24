package ua.logos.service;

import ua.logos.domaim.CommentDTO;

import java.util.List;

public interface CommentService {

    void saveComment(CommentDTO comment);


    List<CommentDTO> findAllComments();

    CommentDTO findCommentById(Long id);

    CommentDTO updateComment(Long id, CommentDTO commentToUpdate);
}
