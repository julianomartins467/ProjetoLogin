package com.example.Iclean.config;


import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.example.Iclean.entities.Usuario;
import com.example.Iclean.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncode;
	
	@Autowired
	private UsuarioRepository usuarioRepository;


	@Override
	public void run(String... args) throws Exception {

		// Cleanup the tables
		//usuarioRepository.deleteAllInBatch();
		//especialidadeRepository.deleteAllInBatch();
		
		
		//Users
		Usuario u1 = new Usuario(null, "Joao da Silva", "123456789", passwordEncode.encode("senha123"), "joao@gmail.com");
		Usuario u2 = new Usuario(null, "Maria Ribeiro", "0374561566",  passwordEncode.encode("senha321"), "maria@gmail.com");	
		
		usuarioRepository.saveAll(Arrays.asList(u1, u2));
		
	}
}
