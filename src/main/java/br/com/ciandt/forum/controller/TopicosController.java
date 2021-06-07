package br.com.ciandt.forum.controller;

import br.com.ciandt.forum.dto.TopicoDto;
import br.com.ciandt.forum.form.AtualizacaoTopicoForm;
import br.com.ciandt.forum.form.TopicoForm;
import br.com.ciandt.forum.model.Topico;
import br.com.ciandt.forum.repository.CursoRepository;
import br.com.ciandt.forum.repository.TopicoRepository;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> lista(String nomeCurso){
        if(nomeCurso == null) {
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDto.converter(topicos);
        }else{
            List<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso);
            return TopicoDto.converter(topicos);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Validated TopicoForm form, UriComponentsBuilder uriComponentsBuilder){
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return  ResponseEntity.created(uri).body(new TopicoDto(topico));
    }


    @PutMapping ("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id , @RequestBody @Validated AtualizacaoTopicoForm form){
        Topico topico = form.atualizar(id, topicoRepository);

        return  ResponseEntity.ok(new TopicoDto(topico));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id){
        topicoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
