package it.universita.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.universita.model.Materia;

public interface MateriaRepository extends JpaRepository<Materia, Integer> {

	Optional<Materia> findByNome(String nome);

}
