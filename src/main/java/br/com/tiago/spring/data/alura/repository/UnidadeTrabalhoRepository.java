package br.com.tiago.spring.data.alura.repository;

import br.com.tiago.spring.data.alura.models.UnidadeTrabalho;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeTrabalhoRepository extends CrudRepository<UnidadeTrabalho, Long> {
}
