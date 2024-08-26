package team_project.clat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import team_project.clat.event.CourseInsertEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@SpringBootApplication
@EnableJpaAuditing
public class ClatApplication {

	@Autowired
	private ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(ClatApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		Resource resource = new ClassPathResource("courses.csv");
//
//		try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
//
//			String line;
//			br.readLine();
//			while ((line = br.readLine()) != null) {
//				String[] fields = line.split(",");
//				String courseCode = fields[0].trim();
//				String courseName = fields[1].trim();
//				String room = fields[2].trim();
//				String startDate = fields[3].trim();
//				String endDate = fields[4].trim();
//				String dayOfWeek = fields[5].trim();
//
//				applicationContext.publishEvent(new CourseInsertEvent(
//								this, courseCode, courseName, room, startDate, endDate, dayOfWeek
//				));
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}