package com.mfelton.Controller;

import com.mfelton.Service.PaiementService;
import com.mfelton.model.Client;
import com.mfelton.model.Paiement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class PaiementController {

    private final PaiementService paiementService;

    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @PostMapping(path = "/paiement")
    public ResponseEntity<Paiement> createClient(@RequestBody Paiement paiement){
        return paiementService
                .addPaiement(paiement)
                .map(client1 -> ResponseEntity.status(HttpStatus.CREATED).body(client1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping(path = "/paiements")
    public ResponseEntity<List<Paiement>> getAllPaiements() {
        return new ResponseEntity<>(paiementService.getAllPaiements(), HttpStatus.OK);
    }

    @GetMapping("/paiement/{email}")
    public ResponseEntity<Paiement> findPaimentByCourriel(@PathVariable("email") String email) {
        return paiementService
                .findPaiementByCourriel(email)
                .map(user1 -> ResponseEntity.status(HttpStatus.OK).body(user1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }
}
