package br.com.cinemaflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class CinemaflixApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaflixApplication.class, args);
	}
}
