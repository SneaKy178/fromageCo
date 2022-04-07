package com.mfelton.Repository;

import com.mfelton.model.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur, Integer> {

    Administrateur findAdministrateurById(int id);

    Administrateur findAministrateurByCourrielIgnoreCase(String courriel);

    Administrateur findAdministrateurByCourrielIgnoreCaseAndPassword(String courriel, String pwd);
}
