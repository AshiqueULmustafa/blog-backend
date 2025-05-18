package com.ashique.blog.post;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


public record Post(


        Long id,
        @NotEmpty
        String title,
        @NotEmpty
        String content,

        String author,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {

}