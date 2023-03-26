package com.algaworks.deliveryfood.controller;

import com.algaworks.deliveryfood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.deliveryfood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.deliveryfood.domain.model.Estado;
import com.algaworks.deliveryfood.domain.repository.EstadoRepository;
import com.algaworks.deliveryfood.domain.usecase.CadastroEstadoUseCase;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoUseCase cadastroEstadoUseCase;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{estadoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Estado> buscarEstadoPorId(@PathVariable Long estadoId) {
        Estado estado = estadoRepository.findById(estadoId)
                .orElseThrow(() ->
                        new EstadoNaoEncontradoException(estadoId));

        if (estado != null) {
            return ResponseEntity.ok(estado);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long estadoId,
            @RequestBody Estado estado) {
        try{
        Estado estadoAtual = estadoRepository.findById(estadoId)
                .orElseThrow(() ->
                        new EstadoNaoEncontradoException(estadoId));

        if (estadoAtual != null) {
            BeanUtils.copyProperties(estado, estadoAtual, "id");
            estadoAtual = cadastroEstadoUseCase.salvar(estadoAtual);
            return ResponseEntity.ok(estadoAtual);
        }
        return ResponseEntity.notFound().build();
    } catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEstadoNaoEncontradoException(EntidadeNaoEncontradaException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
