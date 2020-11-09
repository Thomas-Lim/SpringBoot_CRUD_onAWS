package com.aws.deploy.CRUDAWStest.web.dto;

import com.aws.deploy.CRUDAWStest.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String conent;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.conent = entity.getContent();
        this.author = entity.getAuthor();
    }

}
