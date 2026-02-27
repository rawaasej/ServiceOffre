package com.iset.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.iset.demo.entities.Offre;
import com.iset.demo.dao.OffreRepository;

@RestController
@RequestMapping("/offres")
public class OffreController {

    @Autowired
    private OffreRepository offreRepository;

 
    @GetMapping
    public List<Offre> getAll() {
        return offreRepository.findAll();
    }

    @GetMapping("/{id}")
    public Offre getById(@PathVariable Long id) {
        return offreRepository.findById(id).orElse(null);
    }


    @PostMapping
    public Offre create(@RequestBody Offre offre) {
        return offreRepository.save(offre);
    }


    @PutMapping("/{id}")
    public Offre update(@PathVariable Long id, @RequestBody Offre offre) {
        Offre existing = offreRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setIntitule(offre.getIntitule());
            existing.setSpecialite(offre.getSpecialite());
            existing.setSociete(offre.getSociete());
            existing.setNbpostes(offre.getNbpostes());
            existing.setPays(offre.getPays());
            return offreRepository.save(existing);
        }
        return null;
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        offreRepository.deleteById(id);
        return "Offre supprimée";
    }
}