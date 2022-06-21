package com.algaworks.deliveryfood.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estados")
public class EstadoController{

//    @Autowired
//    private EstadoRepository estadoRepository;
//
//    @Autowired
//    private CadastroEstadoUseCase cadastroEstadoUseCase;
//
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<Estado> listar() {
//        return estadoRepository.listar();
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping(value = "/{estadoId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Estado> buscarEstadoPorId(@PathVariable Long estadoId) {
//        Estado estado = estadoRepository.buscar(estadoId);
//
//        if (estado != null) {
//            return ResponseEntity.ok(estado);
//        }
//
//        return ResponseEntity.notFound().build();
//    }
//
//    @PutMapping("/{estadoId}")
//    public ResponseEntity<?> atualizar(@PathVariable Long estadoId,
//                                            @RequestBody Estado estado) {
//        try{
//        Estado estadoAtual = estadoRepository.buscar(estadoId);
//
//        if (estadoAtual != null) {
//            BeanUtils.copyProperties(estado, estadoAtual, "id");
//            estadoAtual = cadastroEstadoUseCase.salvar(estadoAtual);
//            return ResponseEntity.ok(estadoAtual);
//        }
//        return ResponseEntity.notFound().build();
//    } catch (EntidadeNaoEncontradaException e){
//            return ResponseEntity.badRequest()
//                    .body(e.getMessage());
//        }
//    }
}
