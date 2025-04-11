package com.dev.TargetSistemas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.dev.TargetSistemas.questoes.Exercicio;

@SpringBootApplication
public class TargetSistemasApplication {

	public static void main(String[] args) {
		SpringApplication.run(TargetSistemasApplication.class, args);


		Exercicio.main(args);
	}
}




