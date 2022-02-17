package com.mfelton.Controller;

import com.mfelton.Service.ClientService;
import com.mfelton.model.Client;
import com.mfelton.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
//@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
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

    @GetMapping("/client/{email}/{password}")
    public ResponseEntity<Client> login(
            @PathVariable("email") String email, @PathVariable("password") String password) {
        return clientService
                .login(email, password)
                .map(etudiant1 -> ResponseEntity.status(HttpStatus.OK).body(etudiant1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping("/client/{email}")
    public ResponseEntity<Client> findUserByEmail(@PathVariable("email") String email) {
        return clientService
                .findClientByCourriel(email)
                .map(user1 -> ResponseEntity.status(HttpStatus.OK).body(user1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }
}
