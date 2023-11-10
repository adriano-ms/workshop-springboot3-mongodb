package com.adrianoms.workshopmongo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.adrianoms.workshopmongo.domain.Post;
import com.adrianoms.workshopmongo.domain.User;
import com.adrianoms.workshopmongo.dto.AuthorDTO;
import com.adrianoms.workshopmongo.dto.CommentDTO;
import com.adrianoms.workshopmongo.repository.PostRepository;
import com.adrianoms.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post p1 = new Post(null, Instant.parse("2023-11-10T10:16:00.00Z"), "Travel to Rio", "Starting my first travel to Rio de Janeiro, I hope everything goes well", new AuthorDTO(maria));
		Post p2 = new Post(null, Instant.parse("2023-11-10T12:32:05.00Z"), "So, finally Rio!", "Everything went well and I'm in Rio de Janeiro", new AuthorDTO(maria));
		Post p3 = new Post(null, Instant.parse("2023-11-01T08:05:01.00Z"), "Job Interview", "Going to my job interview at Microsoft, wish me luck", new AuthorDTO(alex));
		Post p4 = new Post(null, Instant.parse("2023-10-30T09:10:15.00Z"), "John Birthday", "Today is the birthday of my good friend John. Happy Birthday John!", new AuthorDTO(bob));
		
		p1.getComments().add(new CommentDTO("Good travel!", Instant.parse("2023-11-10T10:21:00.00Z"), new AuthorDTO(alex)));
		p2.getComments().add(new CommentDTO("Nice!", Instant.parse("2023-11-10T12:53:05.00Z"), new AuthorDTO(bob)));
		p3.getComments().add(new CommentDTO("Good luck, Alex!", Instant.parse("2023-11-01T08:07:01.00Z"), new AuthorDTO(maria)));
		p4.getComments().add(new CommentDTO("Happy Birthday John!", Instant.parse("2023-10-30T09:32:15.00Z"), new AuthorDTO(alex)));
		p4.getComments().add(new CommentDTO("I want a big party!", Instant.parse("2023-10-30T09:40:15.00Z"), new AuthorDTO(maria)));
		
		postRepository.saveAll(Arrays.asList(p1, p2, p3, p4));		
	
		maria.getPosts().addAll(Arrays.asList(p1,p2));
		alex.getPosts().add(p3);
		bob.getPosts().add(p4);
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
	}
}
