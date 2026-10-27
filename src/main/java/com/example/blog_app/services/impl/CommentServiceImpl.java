package com.example.blog_app.services.impl;

import com.example.blog_app.exception.ResourceNotFoundException;
import com.example.blog_app.models.Comment;
import com.example.blog_app.models.Post;
import com.example.blog_app.payloads.CommentDto;
import com.example.blog_app.repository.CommentRepo;
import com.example.blog_app.repository.PostRepo;
import com.example.blog_app.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }
//delete comment
    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
        commentRepo.delete(comment);
    }
}
