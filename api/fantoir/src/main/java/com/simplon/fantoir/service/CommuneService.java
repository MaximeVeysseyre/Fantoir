package com.simplon.fantoir.service;

import com.simplon.fantoir.model.Commune;
import com.simplon.fantoir.repository.CommuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommuneService implements ICommuneService {

    @Autowired
    private CommuneRepository communeRepository;

    @Override
    public List<Commune> findAll() {

        var communes = (List<Commune>)communeRepository.findAll();
        return communes;
    }
}
