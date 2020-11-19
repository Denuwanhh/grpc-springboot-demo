package demo.employee.service;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import net.devh.boot.grpc.server.serverfactory.GrpcServerConfigurer;

/**
 * @author Denuwan Himanga Hettiarachchi
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class EmployeeService {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeService.class, args);
	}

	@Bean
	public GrpcServerConfigurer keepAliveServerConfigurer() {
		return serverBuilder -> {
			if (serverBuilder instanceof NettyServerBuilder) {
				((NettyServerBuilder) serverBuilder).keepAliveTime(30, TimeUnit.SECONDS)
						.keepAliveTimeout(5, TimeUnit.SECONDS).permitKeepAliveWithoutCalls(true);
			}
		};
	}
}
