package com.ashique.blog.post;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PostRepository {
    Optional<Post> getPostById(int id);

    Optional<Post> getPostByTitle(String title);

    Optional<Post> getPostByAuthor(String author);

    Optional<Post> getPostByDate(LocalDateTime date);

    Optional<Post> getPostByContent(String content);

    void CreatePost(Post post);

    void UpdatePost(Post post, int id);
}
