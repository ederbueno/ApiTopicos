package br.com.ciandt.forum.form;

import br.com.ciandt.forum.model.Curso;
import br.com.ciandt.forum.model.Topico;
import br.com.ciandt.forum.repository.CursoRepository;
import com.sun.istack.NotNull;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.hibernate.annotations.BatchSize;


public class TopicoForm {

    @NotNull
    @BatchSize(size = 5)
    private String titulo;
    @NotNull
    @BatchSize(size = 5)
    private String mensagem;
    @NotNull
    @BatchSize(size = 5)
    private String nomeCurso;


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Topico converter(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(nomeCurso);
        return new Topico(titulo, mensagem, curso);
    }
}
