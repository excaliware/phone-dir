package phonedir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * This is the main class; contains the main method.
 */
@SpringBootApplication
public class PhoneDirApplication extends SpringBootServletInitializer  {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder app) {
		return app.sources(PhoneDirApplication.class);
	}
	
	public static void main(String[] args) throws Exception {
    	
        SpringApplication.run(PhoneDirApplication.class, args);
    }
}
