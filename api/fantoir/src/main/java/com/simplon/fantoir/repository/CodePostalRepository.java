package com.simplon.fantoir.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.simplon.fantoir.model.CodePostal;

@Repository
public interface CodePostalRepository extends CrudRepository<CodePostal, Long> {
}