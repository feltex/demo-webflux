package br.com.feltex.demowebflux;

import java.time.Instant;
import org.springframework.data.annotation.Id;

public record Aluno(@Id Long id, String nome, String telefone, String email, Instant dataCadastro) {

}
