package it.universita.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.universita.model.Studente;

public interface StudenteRepository extends JpaRepository<Studente, Integer> {

	Optional<Studente> findByNome(String nome);

}
