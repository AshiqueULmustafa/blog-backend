package com.ashique.blog.post;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record Post(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,

        @NotBlank
        @Size(max = 255)
        String title,

        @NotBlank
        @Column(columnDefinition = "TEXT")
        String content,

        @NotBlank
        String author,

        @NotNull
        LocalDateTime createdAt,

        @NotNull
        LocalDateTime updatedAt
) {
}