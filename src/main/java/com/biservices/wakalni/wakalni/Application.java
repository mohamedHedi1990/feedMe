package com.biservices.wakalni.wakalni;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.biservices.wakalni.wakalni.config.FileStorageProperties;
import com.biservices.wakalni.wakalni.persistence.repositories.UserRepository;

/**
 * Main class of application<br>
 * 
 * Spring Boot tries to guess the location of your {@link Repository}, based on
 * the {@link EnableAutoConfiguration} annotation it finds. So we have to place
 * {@link EnableJpaRepositories} annotation below {@link SpringBootApplication}
 * annotation
 * 
 * @author Wenbo Wang (jackie-1685@163.com)
 * 
 */

@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
public class Application implements CommandLineRunner  {

@Autowired
private UserRepository userRepo;
	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
		System.out.println("Feed Me server was successufully started");
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/*PlatformUser user = new PlatformUser();
		user.setUsername("test");
		user.setPassword("test");
		userRepo.save(user);*/
//		String loginPasswordString = "test:test";
//	      String encodingString = Base64.getEncoder().encodeToString(loginPasswordString.getBytes());
//	      System.out.println("encodingString " + encodingString);
	}

}
