package com.mfelton.Service;

import com.mfelton.Repository.PaiementRepository;
import com.mfelton.model.Client;
import com.mfelton.model.Paiement;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaiementService {

    private final PaiementRepository paiementRepository;

    public PaiementService(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    public Optional<Paiement> addPaiement(Paiement paiement){
        return Optional.of(paiementRepository.save(paiement));
    }

    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }

    public Optional<Paiement> findPaiementByCourriel(String email) {
        return Optional.of(paiementRepository.findPaiementByClientCourriel(email));
    }
}
