package com.adrianoms.workshopmongo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.adrianoms.workshopmongo.domain.Post;
import com.adrianoms.workshopmongo.domain.User;
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
		
		Post p1 = new Post(null, Instant.parse("2023-11-10T10:16:00.00Z"), "Travel to Rio", "Starting my first travel to Rio de Janeiro, I hope everything goes well", maria);
		Post p2 = new Post(null, Instant.parse("2023-11-10T12:32:05.00Z"), "So, finally Rio!", "Everything went well and I'm in Rio de Janeiro", maria);
		Post p3 = new Post(null, Instant.parse("2023-11-01T08:05:01.00Z"), "Job Interview", "Going to my job interview at Microsoft, wish me luck", alex);
		Post p4 = new Post(null, Instant.parse("2023-10-30T09:10:15.00Z"), "John Birthday", "Today is the birthday of my good friend John. Happy Birthday John!", bob);
		
		postRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
	}
}
