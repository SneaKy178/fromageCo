package com.mfelton.Repository;

import com.mfelton.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {

    Client findClientById(int id);

    Client findClientByCourrielIgnoreCase(String courriel);

    Client findClientByCourrielIgnoreCaseAndPassword(String courriel, String pwd);
}
