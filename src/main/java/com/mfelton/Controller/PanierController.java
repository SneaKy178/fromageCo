package com.mfelton.Controller;

import com.mfelton.Service.PanierService;
import com.mfelton.model.Client;
import com.mfelton.model.Panier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class PanierController {

    private final PanierService panierService;

    public PanierController(PanierService panierService) {
        this.panierService = panierService;
    }

    @PostMapping(path = "/panier")
    public ResponseEntity<Panier> createClient(@RequestBody Panier panier){
        return panierService
                .addPanier(panier)
                .map(panier1 -> ResponseEntity.status(HttpStatus.CREATED).body(panier1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping(path = "/paniers")
    public ResponseEntity<List<Panier>> getAllPaniers() {
        return new ResponseEntity<>(panierService.getAllPaniers(), HttpStatus.OK);
    }

    @GetMapping("/panier/{id}")
    public ResponseEntity<Panier> findPanierByClientId(@PathVariable("id") int id) {
        return panierService
                .findClienById(id)
                .map(user1 -> ResponseEntity.status(HttpStatus.OK).body(user1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }
}
