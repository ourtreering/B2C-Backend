package com.sillock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync
@EnableJpaAuditing
@SpringBootApplication
public class SillockApplication {

	public static void main(String[] args) {
		SpringApplication.run(SillockApplication.class, args);
	}

}
