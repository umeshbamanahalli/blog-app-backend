package com.example.blog_app.controllers;


import com.example.blog_app.payloads.ApiResponse;
import com.example.blog_app.payloads.CommentDto;
import com.example.blog_app.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId) {
     CommentDto createdComment = this.commentService.createComment(commentDto,postId);
    return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
         this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("comment is deleted successfully!!",true), HttpStatus.OK);
    }

}
