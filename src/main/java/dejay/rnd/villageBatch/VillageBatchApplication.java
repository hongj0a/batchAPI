package dejay.rnd.villageBatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VillageBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(VillageBatchApplication.class, args);
	}

}
