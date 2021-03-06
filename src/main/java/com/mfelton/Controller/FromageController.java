package com.mfelton.Controller;

import com.mfelton.Repository.FromageRepository;
import com.mfelton.Service.FromageService;
import com.mfelton.model.Fromage;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class FromageController {

    private final FromageService fromageService;
    private final FromageRepository fromageRepository;

    public FromageController(FromageService fromageService, FromageRepository fromageRepository) {
        this.fromageService = fromageService;
        this.fromageRepository = fromageRepository;
    }

    @PostMapping(path = "/fromage")
    public ResponseEntity<Fromage> addFromage(@RequestBody Fromage fromage) {
        return fromageService
                .addFromage(fromage)
                .map(fromage1 -> ResponseEntity.status(HttpStatus.CREATED).body(fromage1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping(path = "/fromages")
    public ResponseEntity<List<Fromage>> getAllFromages() {
        return new ResponseEntity<>(fromageService.getAllFromages(), HttpStatus.OK);
    }

    @GetMapping(path = "fromages/images/{id}")
    public void generateImage(@PathVariable("id") int id, HttpServletResponse response) {
        try {
            Fromage fromage = fromageRepository.getById(id);
            InputStream inputStream =
                    new ByteArrayInputStream(fromage.getData());
            IOUtils.copy(inputStream, response.getOutputStream());
            ResponseEntity.status(HttpStatus.OK).build();
        } catch (IOException e) {
            e.printStackTrace();
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(path = "/fromage/delete/{id}")
    public ResponseEntity deleteFromage(@PathVariable int id) {
        fromageService.deleteFromage(id);
        return ResponseEntity.ok().build();
    }
}
