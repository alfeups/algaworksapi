package com.algaworks.deliveryfood.controller;

import com.algaworks.deliveryfood.domain.model.Estado;
import com.algaworks.deliveryfood.domain.repository.EstadoRepository;
import com.algaworks.deliveryfood.domain.usecase.CadastroEstadoUseCase;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        var estado = cadastroEstadoUseCase.buscarOuRetornarException(estadoId);

        return ResponseEntity.ok(estado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody @Valid Estado estado) {
        return cadastroEstadoUseCase.salvar(estado);
    }

        @PutMapping("/{estadoId}")
    public ResponseEntity<?> atualizar(@PathVariable Long estadoId,
                                       @RequestBody Estado estado) {
        var estadoAtual = cadastroEstadoUseCase.buscarOuRetornarException(estadoId);
        BeanUtils.copyProperties(estado, estadoAtual, "id");

        return ResponseEntity.ok(estadoAtual);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        cadastroEstadoUseCase.excluir(estadoId);
    }

}
