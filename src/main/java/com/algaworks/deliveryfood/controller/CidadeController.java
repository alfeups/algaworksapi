package com.algaworks.deliveryfood.controller;

import com.algaworks.deliveryfood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.deliveryfood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.deliveryfood.domain.exception.NegocioException;
import com.algaworks.deliveryfood.domain.model.Cidade;
import com.algaworks.deliveryfood.domain.repository.CidadeRepository;
import com.algaworks.deliveryfood.domain.usecase.CadastroCidadeUseCase;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeUseCase cadastroCidadeUseCase;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{cidadeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cidade> buscarCidadePorId(@PathVariable Long cidadeId) {
        var cidade = cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
        return ResponseEntity.ok(cidade);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody Cidade cidade){
        try {
            return cidadeRepository.save(cidade);
        } catch (EstadoNaoEncontradoException e){
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Cidade atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
        var cidadeAtual = cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));

        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        return cidadeAtual = cadastroCidadeUseCase.salvar(cidadeAtual);
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeAId) {
            cadastroCidadeUseCase.excluir(cidadeAId);
    }

}
