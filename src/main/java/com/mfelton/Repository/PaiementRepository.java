package com.mfelton.Repository;

import com.mfelton.model.Client;
import com.mfelton.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement,Integer> {

    Paiement findPaiementByClientCourriel(String courriel);
}
