package com.mfelton;

import com.mfelton.Repository.ClientRepository;
import com.mfelton.model.Client;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FromageCoApplication implements CommandLineRunner {

    private final ClientRepository clientRepository;

    public FromageCoApplication(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(FromageCoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Client client = new Client("Mathieu","Felton","mat@gmail.com","Test1234","123 sreet","51423332","Quebec","Montreal");
        clientRepository.save(client);
    }
}
