package com.ashique.blog;

import com.ashique.blog.post.Post;
import com.ashique.blog.post.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Application {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@Bean
	CommandLineRunner runner(PostRepository postRepository) {
		return args -> {
			Post post = postRepository.getPostById(1).get();
			System.out.println(post);
		};
	}

}
