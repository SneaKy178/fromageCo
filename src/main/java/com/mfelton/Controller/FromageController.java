package com.mfelton.Controller;

import com.mfelton.Service.FromageService;
import com.mfelton.model.Client;
import com.mfelton.model.Fromage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class FromageController {

    private final FromageService fromageService;

    public FromageController(FromageService fromageService) {
        this.fromageService = fromageService;
    }

    @GetMapping(path = "/fromages")
    public ResponseEntity<List<Fromage>> getAllFromages() {
        return new ResponseEntity<>(fromageService.getAllFromages(), HttpStatus.OK);
    }
}
