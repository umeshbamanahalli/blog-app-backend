package com.example.blog_app.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private int id;
    private String content;
}
