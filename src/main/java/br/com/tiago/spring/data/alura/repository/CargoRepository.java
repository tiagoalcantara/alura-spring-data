package br.com.tiago.spring.data.alura.repository;

import br.com.tiago.spring.data.alura.models.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Long> {}
