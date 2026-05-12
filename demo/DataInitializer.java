package com.amatuer3.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.amatuer3.demo.model.User; // Hakikisha path ya User model ni sahihi
import com.amatuer3.demo.repository.UserRepository; // Hakikisha path ya repository ni sahihi

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Angalia kama admin tayari yupo ili usirudie kumtengeneza
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setFirstName("Evodi");
            admin.setLastName("Phinince");
            admin.setMiddleName("Admin");
            admin.setUsername("admin");
            // Hapa tunasimbua password 'admin123'
            admin.setPassword(passwordEncoder.encode("admin123")); 
            admin.setRole("ADMIN");
            admin.setEmail("evodiphinince@gmail.com");
            
            userRepository.save(admin);
            System.out.println(">>>> Admin user initialized successfully!");
        }
    }
}