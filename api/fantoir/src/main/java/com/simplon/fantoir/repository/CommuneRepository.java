package com.simplon.fantoir.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.simplon.fantoir.model.Commune;

@Repository
public interface CommuneRepository extends CrudRepository<Commune, Long> {
}
