package txeim.Logindemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class LogindemoApplication {

	public static void main(String[] args) {
		 ConfigurableApplicationContext Context = SpringApplication.run(LogindemoApplication.class, args);
		 ConfigurableEnvironment con= Context.getEnvironment();
		 System.out.println(con.getActiveProfiles()[0]);
		 System.out.println(System.getProperty("user.dir"));

	}

}
