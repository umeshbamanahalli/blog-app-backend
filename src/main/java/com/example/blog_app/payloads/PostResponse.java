package com.example.blog_app.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
    private List<PostDto> content;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private int totalElements;
    private boolean lastPage;

}
