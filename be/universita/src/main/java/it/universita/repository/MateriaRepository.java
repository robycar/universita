package it.universita.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.universita.model.Materia;

public interface MateriaRepository extends JpaRepository<Materia, Integer> {

}
