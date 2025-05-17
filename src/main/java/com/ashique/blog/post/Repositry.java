package com.ashique.blog.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class Repositry implements PostRepository{

    private static final Logger log = LoggerFactory.getLogger(Repositry.class);
    private final List<Post> posts;

    public Repositry(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public void CreatePost(Post post) {
        posts.add(post);
    }

    @Override
    public void UpdatePost(Post newPost, int id) {
        Optional<Post> existingPost = getPostById(id);
        if(existingPost.isPresent()) {
            var r = existingPost.get();
            log.info("Updating Existing Run: " + existingPost.get());
            posts.set(posts.indexOf(r),newPost);
        }
    }

    @Override
    public Optional<Post> getPostById(int id) {
        return java.util.Optional.ofNullable(posts.stream()
                .filter(post -> post.id() == id)
                .findFirst()
                .orElseThrow(PostNotFoundException::new));
    }

    @Override
    public Optional<Post> getPostByTitle(String title) {
        return java.util.Optional.ofNullable(posts.stream()
                .filter(post -> post.title().equals(title))
                .findFirst()
                .orElseThrow(PostNotFoundException::new));
    }

    @Override
    public Optional<Post> getPostByAuthor(String author) {
        return java.util.Optional.ofNullable(posts.stream()
                .filter(post -> post.author().equals(author))
                .findFirst()
                .orElseThrow(PostNotFoundException::new));
    }

    @Override
    public Optional<Post> getPostByDate(LocalDateTime date) {
        return java.util.Optional.ofNullable(posts.stream()
                .filter(post -> post.createdAt().equals(date))
                .findFirst()
                .orElseThrow(PostNotFoundException::new));
    }

    @Override
    public Optional<Post> getPostByContent(String content) {
        return java.util.Optional.ofNullable(posts.stream()
                .filter(post -> post.content().equals(content))
                .findFirst()
                .orElseThrow(PostNotFoundException::new));
    }

}
