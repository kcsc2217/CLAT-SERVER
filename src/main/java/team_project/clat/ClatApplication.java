package team_project.clat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ClatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClatApplication.class, args);
	}

}
