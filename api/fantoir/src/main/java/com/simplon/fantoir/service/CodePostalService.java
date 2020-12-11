package com.simplon.fantoir.service;

import com.simplon.fantoir.repository.CodePostalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.simplon.fantoir.model.CodePostal;

import java.util.List;

@Service
public class CodePostalService implements ICodePostalService{

    @Autowired
    private CodePostalRepository codePostalRepository;

    @Override
    public List<CodePostal> findAll() {
        var codePostaux = (List<CodePostal>) codePostalRepository.findAll();
        return codePostaux;
    }
}