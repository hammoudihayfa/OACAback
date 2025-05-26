package tn.esprit.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator gatwayRutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("logiciels-service", r -> r.path("/logiciels-service/**").uri("http://localhost:8084"))
				.route("agents", r -> r.path("/agents/**").uri("http://localhost:8082"))
				.route("annonces", r -> r.path("/annonces/**").uri("http://localhost:8082"))
				.route("notification", r -> r.path("/notification/**").uri("http://localhost:8082"))
				.route("liste-equipements", r -> r.path("/liste-equipements/**").uri("http://localhost:8082"))
				.route("programme-maintenance", r -> r.path("/programme-maintenance/**").uri("http://localhost:8083"))
				.route("api", r -> r.path("/api/**").uri("http://localhost:8083"))
				.route("rapports", r -> r.path("/rapports/**").uri("http://localhost:8083"))
				.route("equipements", r -> r.path("/equipements/**").uri("http://localhost:8081"))
				.route("equipements-en-panne", r -> r.path("/equipements-en-panne/**").uri("http://localhost:8081"))
				.route("equipements-en-service", r -> r.path("/equipements-en-service/**").uri("http://localhost:8081"))
				.route("equipements-en-stock", r -> r.path("/equipements-en-stock/**").uri("http://localhost:8081"))
				.route("etat-equipement", r -> r.path("/etat-equipement/**").uri("http://localhost:8081"))
				.route("equipementsReformes", r -> r.path("/equipementsReformes/**").uri("http://localhost:8081"))
				.route("equipements-repartition", r -> r.path("/equipements-repartition/**").uri("http://localhost:8081"))
				.route("equipement-transfere", r -> r.path("/equipement-transfere/**").uri("http://localhost:8081"))
				.route("ocr", r -> r.path("/ocr/**").uri("http://localhost:8080"))
				.route("users", r -> r.path("/users/**").uri("http://localhost:8088"))

				.build();

	}


}