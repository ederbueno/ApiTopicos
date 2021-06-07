package br.com.ciandt.forum.repository;

import br.com.ciandt.forum.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

        Curso findByNome(String nome);
}
