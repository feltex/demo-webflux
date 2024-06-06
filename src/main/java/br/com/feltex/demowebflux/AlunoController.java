package br.com.feltex.demowebflux;

import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/alunos")
@Slf4j
public record AlunoController(AlunoRepository alunoRepository) {

    @GetMapping("{id}")
    public Mono<Aluno> buscarPorId(@PathVariable Long id) {

        try {
            return alunoRepository.findById(id);
        } catch (Exception e) {
            log.error("Aluno não encontrado alunoId {}", id, e);
        }
        return Mono.empty();
    }

    @GetMapping
    public Flux<Aluno> listarTodos(){
        return alunoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Aluno> adicionar(@RequestBody Aluno alunoRequest){
        final var aluno = new Aluno(null, alunoRequest.nome(), alunoRequest.telefone(), alunoRequest.email(),
        Instant.now());
        return alunoRepository.save(aluno);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletarPorId(@PathVariable Long id){
        return alunoRepository.deleteById(id);
    }

}
