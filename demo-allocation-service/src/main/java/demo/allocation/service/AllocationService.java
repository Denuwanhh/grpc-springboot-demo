package demo.allocation.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Denuwan Himanga Hettiarachchi
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class AllocationService {

	public static void main(String[] args) {
		SpringApplication.run(AllocationService.class, args);
	}
}
