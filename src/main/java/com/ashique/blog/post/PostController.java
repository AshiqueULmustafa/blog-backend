package com.ashique.blog.post;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/hello")
    String hello() {
        return "Hello World";
    }

    @GetMapping("/")
    List<Post> getAllPost() {
        return postRepository.getAllPost();
    }

    @GetMapping("/id/{id}")
    Post getPostById(@PathVariable int id) {
        Optional<Post> post = postRepository.getPostById(id);
        if (post.isEmpty()) {
            throw new PostNotFoundException();
        }
        return post.get();
    }

    @GetMapping("/title/{title}")
    Post getPostByTitle(@PathVariable String title) {
        Optional<Post> post = postRepository.getPostByTitle(title);
        if (post.isEmpty()) {
            throw new PostNotFoundException();
        }
        return post.get();
    }

    @GetMapping("/author/{author}")
    List<Post> getPostByAuthor(@PathVariable String author) {
        List<Post> post = postRepository.getPostByAuthor(author);
        if (post.isEmpty()) {
            throw new PostNotFoundException();
        }
        return post;
    }

    @GetMapping("/date/{date}")
    List<Post> getPostByDate(@PathVariable LocalDateTime date) {
        List<Post> post = postRepository.getPostByDate(date);
        if (post.isEmpty()) {
            throw new PostNotFoundException();
        }
        return post;
    }

    @GetMapping("/content/{content}")
    Post getPostByContent(@PathVariable String content) {
        Optional<Post> post = postRepository.getPostByContent(content);
        if (post.isEmpty()) {
            throw new PostNotFoundException();
        }
        return post.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    void createPost(@RequestBody Post post) {
        try {
            postRepository.createPost(post);
        } catch (Exception e) {
            throw new RuntimeException("A post with the same identifier already exists.");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void updatePost(@RequestBody Post post, @PathVariable int id) {
        postRepository.updatePost(post, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deletePost(@PathVariable int id) {
        postRepository.deletePost(id);
    }

}
