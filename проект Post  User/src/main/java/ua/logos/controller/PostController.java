package ua.logos.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.logos.domaim.PostDTO;
import ua.logos.entity.PostEntity;
import ua.logos.service.PostService;

import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController  {

    @Autowired
    private PostService postService;


    @PostMapping
    public ResponseEntity<?> createPost(
            @RequestBody PostDTO postDTO
            ){

        System.out.println(
                postDTO.getTitle()+ " "+
                        postDTO.getDescription()+" "+
                        postDTO.getFirstDate()
        );
        postService.savePost(postDTO);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

   @GetMapping
    public  ResponseEntity<?>getPosts(){
List<PostDTO> posts=postService.findAllPosts();
return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping("{postId:[0-9]{1,5}}")
    public  ResponseEntity<?>getPostById(@PathVariable("postId")Long id){
        PostDTO post=postService.findPostById(id);
        return  new ResponseEntity<>(post,HttpStatus.OK);
    }

    @PutMapping("{postId:[0-9]{1,5}}")
    public  ResponseEntity<?>updatePost(
            @PathVariable("postId")Long id,@RequestBody PostDTO postToUpdate
    ){
        PostDTO post=postService.updatePost(id, postToUpdate);

        if (post==null){
            return  new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(post,HttpStatus.OK);
    }


}
