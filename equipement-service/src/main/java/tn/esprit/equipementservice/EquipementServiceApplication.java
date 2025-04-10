package tn.esprit.equipementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "tn.esprit.equipementservice")
@EnableFeignClients
@EnableDiscoveryClient
public class EquipementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquipementServiceApplication.class, args);
	}

}
