package wayne.springboot.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootTaskSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTaskSampleApplication.class, args);
	}

}
