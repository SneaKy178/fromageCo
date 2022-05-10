package com.mfelton.Service;

import com.mfelton.Repository.PanierRepository;
import com.mfelton.model.Panier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PanierService {

    private final PanierRepository panierRepository;

    public PanierService(PanierRepository panierRepository) {
        this.panierRepository = panierRepository;
    }

    public Optional<Panier> addPanier(Panier panier) {
        return Optional.of(panierRepository.save(panier));
    }

    public List<Panier> getAllPaniers() {
        return panierRepository.findAll();
    }


}
