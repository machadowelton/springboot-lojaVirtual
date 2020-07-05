package br.com.loja_virtual;

import br.com.loja_virtual.domain.entities.Funcao;
import br.com.loja_virtual.domain.entities.Usuario;
import br.com.loja_virtual.domain.enums.EFuncao;
import br.com.loja_virtual.services.repositories.FuncaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class LojaVirtualApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LojaVirtualApplication.class, args);
	}

	@Autowired
	private FuncaoRepository funcaoRepository;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
