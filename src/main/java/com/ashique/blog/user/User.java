package com.ashique.blog.user;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public record User(

        Long id,
        @NotEmpty String username,
        @NotEmpty String email,
        @NotEmpty String password,

        LocalDateTime createdAt,

        LocalDateTime updatedAt) {

}
