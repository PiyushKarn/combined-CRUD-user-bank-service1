package abc.first;

import abc.first.Domain.UserDomain;
import abc.first.Services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@ComponentScan
//@EnableJpaRepositories(basePackages = "abc.first.Repositories")
public class FirstServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstServiceApplication.class, args);
	}


//	@Bean
//	CommandLineRunner runner(UserService userService){
//		return args -> {
//			// read JSON and load json
//			ObjectMapper mapper = new ObjectMapper();
//			TypeReference<List<UserDomain>> typeReference = new TypeReference<List<UserDomain>>(){};
//			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/users.json");
//			try {
//				List<UserDomain> users = mapper.readValue(inputStream,typeReference);
//				userService.save(users);
//				System.out.println("Users Saved!");
//			} catch (IOException e){
//				System.out.println("Unable to save users: " + e.getMessage());
//			}
//		};
//	}


}
