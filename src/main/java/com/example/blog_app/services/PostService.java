package com.example.blog_app.services;

import com.example.blog_app.models.Post;
import com.example.blog_app.payloads.PostDto;
import com.example.blog_app.payloads.PostResponse;

import java.util.List;

public interface PostService {
    //    create
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    //    update
    PostDto updatePost(PostDto postDto, Integer postId);

    //    delete
    void deletePost(Integer postId);

    //get all posts
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String  sortDir);

    //get post by id
    PostDto getPostById(Integer id);


//    get post by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);
}
