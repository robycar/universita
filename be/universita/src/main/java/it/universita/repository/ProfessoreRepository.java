package it.universita.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.universita.model.Professore;

public interface ProfessoreRepository extends JpaRepository<Professore, Integer> {

}
