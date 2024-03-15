package io.sbstnlambert.serverpilot;

import io.sbstnlambert.serverpilot.model.Server;
import io.sbstnlambert.serverpilot.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

import static io.sbstnlambert.serverpilot.enumeration.Status.SERVER_DOWN;
import static io.sbstnlambert.serverpilot.enumeration.Status.SERVER_UP;

@SpringBootApplication
public class ServerPilotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerPilotApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepository serverRepository) {
		return args -> {
			serverRepository.save(new Server
					(
							null,
							"192.168.1.160",
							"Windows 10",
							"16 GB",
							"PC",
							"http://localhost:8080/servers/images/server1.png",
							SERVER_UP
					)
			);
			serverRepository.save(new Server
					(
							null,
							"192.168.56.1",
							"Unknown",
							"Unknown",
							"Unknown",
							"http://localhost:8080/servers/images/server2.png",
							SERVER_UP
					)
			);
			serverRepository.save(new Server
					(
							null,
							"169.172.15.2",
							"Ubuntu Linux",
							"8 GB",
							"PC",
							"http://localhost:8080/servers/images/server3.png",
							SERVER_DOWN
					)
			);
			serverRepository.save(new Server
					(
							null,
							"172.18.80.1",
							"Windows 10",
							"16 GB",
							"Be Quiet! Tower",
							"http://localhost:8080/servers/images/server4.png",
							SERVER_UP
					)
			);
		};
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
