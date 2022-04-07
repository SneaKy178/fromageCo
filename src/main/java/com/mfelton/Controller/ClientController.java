package com.mfelton.Controller;

import com.mfelton.Repository.PaiementRepository;
import com.mfelton.Service.ClientService;
import com.mfelton.model.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ClientController {

    private final ClientService clientService;
    private final PaiementRepository paiementRepository;

    public ClientController(ClientService clientService, PaiementRepository paiementRepository) {
        this.clientService = clientService;
        this.paiementRepository = paiementRepository;
    }

    @PostMapping(path = "/client")
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        return clientService
                .addClient(client)
                .map(client1 -> ResponseEntity.status(HttpStatus.CREATED).body(client1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping(path = "/clients")
    public ResponseEntity<List<Client>> getAllClients() {
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }


}
