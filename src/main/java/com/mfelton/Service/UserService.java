package com.mfelton.Service;

import com.mfelton.Repository.AdministrateurRepository;
import com.mfelton.Repository.ClientRepository;
import com.mfelton.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final ClientRepository clientRepository;
    private final AdministrateurRepository administrateurRepository;

    public UserService(ClientRepository clientRepository, AdministrateurRepository administrateurRepository) {
        this.clientRepository = clientRepository;
        this.administrateurRepository = administrateurRepository;

    }

    public Optional<User> login(String email, String pwd) {
        if (clientRepository.findClientByCourrielIgnoreCase(email) != null) {
            return Optional.of(
                    clientRepository.findClientByCourrielIgnoreCaseAndPassword(email, pwd));
        }
        if (administrateurRepository.findAministrateurByCourrielIgnoreCase(email) != null) {
            return Optional.of(
                    administrateurRepository.findAdministrateurByCourrielIgnoreCaseAndPassword(email, pwd));
        }
        return Optional.empty();
    }

    public Optional<User> findUserByCourriel(String email) {
        if (clientRepository.findClientByCourrielIgnoreCase(email) != null) {
            return Optional.of(clientRepository.findClientByCourrielIgnoreCase(email));
        }
        if (administrateurRepository.findAministrateurByCourrielIgnoreCase(email) != null) {
            return Optional.of(administrateurRepository.findAministrateurByCourrielIgnoreCase(email));
        }
        return Optional.empty();
    }


}
