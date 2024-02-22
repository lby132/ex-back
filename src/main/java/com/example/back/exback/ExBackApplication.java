package com.example.back.exback;

import com.example.back.exback.aop.TestAspect;
import com.example.back.exback.aop.LogTraceAspect;
import com.example.back.exback.trace.LogTrace;
import com.example.back.exback.trace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Import({TestAspect.class, LogTraceAspect.class})
@EnableJpaAuditing
@SpringBootApplication
public class ExBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExBackApplication.class, args);
	}

	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalLogTrace();
	}
}
