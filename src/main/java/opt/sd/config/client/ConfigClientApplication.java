package opt.sd.config.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * The entry point of client application
 * 
 * @author tsdai
 */
@SpringBootApplication
public class ConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}
}


/**
 * The rest endpoint for demontration purpose of feeding properties value from config server
 * 
 * @author tsdai
 */
@RestController
@RefreshScope	// JUST FOR DEVELOPMENT. by default, client reads configuration on startup and not again -> force bean to refresh its configuration
class MessageRestController {

	@Autowired
	private ClientConfiguration clientConfiguration;
	
	@Value("${user.role}")
	private String role;
	
	@Value("${user.password}")
    private String password;

	@Value("${user.email}")
	private String email;

	@GetMapping(value = "/whoami/{username}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String whoami(@PathVariable("username") String username) {
		
		return  // use config value from @Value
				String.format("Hello from @Value! You're %s and you'll become a(n) %s, " 
				+ "but only if your password is '%s'\n" 
				+ "and your email is '%s'!\n\n", 
		          username, role, password, email)
				+  // use config value from @ConfigurationProperties
				String.format("Hello from @ConfigurationProperties! You're %s and you'll become a(n) %s, " 
				+ "but only if your password is '%s'\n" 
				+ "and your email is '%s'!\n", 
		          username, clientConfiguration.getRole(), clientConfiguration.getPassword(), clientConfiguration.getEmail());
	}
}