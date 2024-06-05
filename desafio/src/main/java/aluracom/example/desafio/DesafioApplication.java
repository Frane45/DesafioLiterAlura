package aluracom.example.desafio;

import aluracom.example.desafio.principal.Principal;
import aluracom.example.desafio.repositorio.Autorrepository;
import aluracom.example.desafio.repositorio.Librorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioApplication implements CommandLineRunner {
	@Autowired
	private Librorepository repository;

	@Autowired
	private Autorrepository autorrepository;

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}

	//@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository, autorrepository);
		principal.MuestraElMenu();
	}
}