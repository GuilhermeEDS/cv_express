package br.com.cv_express;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("br.com.cv_express.*")
@ComponentScan(basePackages = { "br.com.cv_express.*" })
@EntityScan("br.com.cv_express.*")
public class CvExpressApplication {

	public static void main(String[] args) {
		SpringApplication.run(CvExpressApplication.class, args);
	}

}
