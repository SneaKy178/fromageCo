package com.mfelton.Service;

import com.mfelton.Repository.ClientRepository;
import com.mfelton.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Optional<Client> addClient(Client client){
        return Optional.of(clientRepository.save(client));
    }

    public Optional<Client> login(String email, String pwd) {
            return Optional.of(clientRepository.findClientByCourrielIgnoreCaseAndPassword(email, pwd));
    }

    public Optional<Client> findClientByCourriel(String email) {
            return Optional.of(clientRepository.findClientByCourrielIgnoreCase(email));
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
