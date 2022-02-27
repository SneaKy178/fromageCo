package com.mfelton.Repository;

import com.mfelton.model.Fromage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FromageRepository extends JpaRepository<Fromage,Integer> {
}
