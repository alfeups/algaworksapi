package com.algaworks.deliveryfood.controller;

import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/cidades")
public class CidadeController {
//
//    @Autowired
//    private CidadeRepository cidadeRepository;
//
//    @Autowired
//    private CadastroCidadeUseCase cadastroCidadeUseCase;
//
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<Cidade> listar() {
//        return cidadeRepository.todasCidades();
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping(value = "/{cidadeId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Cidade> buscarCidadePorId(@PathVariable Long cidadeId) {
//        Cidade cidade = cidadeRepository.buscarCidadePorId(cidadeId);
//
//        if (cidade != null) {
//            return ResponseEntity.ok(cidade);
//        }
//
//        return ResponseEntity.notFound().build();
//    }
//
//    @PutMapping("/{cidadeId}")
//    public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId,
//                                            @RequestBody Cidade cidade) {
//        Cidade cidadeAtual = cidadeRepository.buscarCidadePorId(cidadeId);
//
//        if (cidadeAtual != null) {
//            BeanUtils.copyProperties(cidade, cidadeAtual, "id");
//
//            cidadeAtual = cadastroCidadeUseCase.salvar(cidadeAtual);
//            return ResponseEntity.ok(cidadeAtual);
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @DeleteMapping("/{cidadeId}")
//    public ResponseEntity<?> remover(@PathVariable Long cidadeAId) {
//        try {
//            cadastroCidadeUseCase.excluir(cidadeAId);
//            return ResponseEntity.noContent().build();
//
//        } catch (EntidadeNaoEncontradaException e) {
//            return ResponseEntity.notFound().build();
//
//        } catch (EntidadeEmUsoException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT)
//                    .body(e.getMessage());
//        }
//    }

}
