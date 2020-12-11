package com.simplon.fantoir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.simplon.fantoir.model.Commune;
import com.simplon.fantoir.service.ICommuneService;

import java.util.List;

@RestController
@RequestMapping("communes")
public class CommuneController {

    @Autowired
    private ICommuneService communeService;

    @GetMapping(value = "/all")
    public List<Commune> findCommunes(){
        var communes = (List<Commune>) communeService.findAll();

        return communes;
    }
}
