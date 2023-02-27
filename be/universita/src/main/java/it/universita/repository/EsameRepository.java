package it.universita.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.universita.model.Esame;
import it.universita.model.EsameKey;

public interface EsameRepository extends JpaRepository<Esame, EsameKey> {

}
