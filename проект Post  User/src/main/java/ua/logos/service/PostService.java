package ua.logos.service;

import ua.logos.domaim.PostDTO;
import ua.logos.entity.PostEntity;

import java.util.List;

public interface PostService {

    void  savePost(PostDTO post);

    List<PostDTO> findAllPosts();

    PostDTO findPostById(Long id);

    PostDTO updatePost(Long id,PostDTO postToUpdate);
}
