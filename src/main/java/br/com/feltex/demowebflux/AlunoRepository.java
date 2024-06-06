package br.com.feltex.demowebflux;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface AlunoRepository extends R2dbcRepository<Aluno, Long> {

}
