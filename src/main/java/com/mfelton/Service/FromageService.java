package com.mfelton.Service;

import com.mfelton.Repository.FromageRepository;
import com.mfelton.model.Client;
import com.mfelton.model.Fromage;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.From;
import java.util.List;

@Service
public class FromageService {

    private final FromageRepository fromageRepository;

    public FromageService(FromageRepository fromageRepository) {
        this.fromageRepository = fromageRepository;
    }

    public List<Fromage> getAllFromages() {
        return fromageRepository.findAll();
    }
}
