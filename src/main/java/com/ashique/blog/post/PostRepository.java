package com.ashique.blog.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository{

    private static final Logger log = LoggerFactory.getLogger(PostRepository.class);
    private final JdbcClient jdbcClient;

    public PostRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    public void createPost(Post post) {
        var updated = jdbcClient.sql("INSERT INTO Post(id, title, created_at, updated_at, content, author) VALUES (?, ?, ?, ?, ?, ?)")
                .params(List.of(post.id(), post.title(), post.createdAt(), post.updatedAt(), post.content(), post.author()))
                .update();

        Assert.state(updated == 1, "Failed to create post" + post.title());
    }


    public void updatePost(Post newPost, int id) {
        var updated = jdbcClient.sql("UPDATE Post SET title = ?, created_at = ?, updated_at = ?, content = ?, author = ? WHERE id = ?")
                .params(List.of(newPost.title(), newPost.createdAt(), newPost.updatedAt(), newPost.content(), newPost.author(), id))
                .update();

        Assert.state(updated == 1, "Failed to update post " + newPost.title());
    }

    public Optional<Post> getPostById(int id) {
        return jdbcClient.sql("SELECT id, title, created_at, updated_at, content, author FROM Post WHERE id = :id")
                .param("id", id)
                .query(Post.class)
                .optional();
    }


    public List<Post> getAllPost() {
        return jdbcClient.sql("SELECT * FROM Post")
                .query(Post.class)
                .list();
    }


    public Optional<Post> getPostByTitle(String title) {
        return jdbcClient.sql("SELECT id, title, created_at, updated_at, content, author FROM Post WHERE title = :title")
                .param("title", title)
                .query(Post.class)
                .optional();
    }

    public List<Post> getPostByAuthor(String author) {
        return jdbcClient.sql("SELECT * FROM Post WHERE author = :author")
                .param("author", author)
                .query(Post.class)
                .list();
    }


    public List<Post> getPostByDate(LocalDateTime date) {
        return jdbcClient.sql("SELECT * FROM Post WHERE created_at = :date")
                .param("date", date)
                .query(Post.class)
                .list();
    }


    public Optional<Post> getPostByContent(String content) {
        return jdbcClient.sql("SELECT id, title, created_at, updated_at, content, author FROM Post WHERE content = :content")
                .param("content", content)
                .query(Post.class)
                .optional();
    }
}
