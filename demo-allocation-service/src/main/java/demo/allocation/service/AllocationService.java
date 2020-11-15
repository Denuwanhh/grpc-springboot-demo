package demo.allocation.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
//@EnableEurekaClient
public class AllocationService {
	
    public static void main( String[] args ){
        SpringApplication.run(AllocationService.class, args);
    }
}
