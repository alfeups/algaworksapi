package com.algaworks.deliveryfood.domain.usecase;

import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoUseCase {

//    @Autowired
//    private EstadoRepository estadoRepository;
//
//    public Estado salvar(Estado estado) {
//        return estadoRepository.salvar(estado);
//    }
//
//
//    public void excluir(Long estadoId) {
//        try {
//            estadoRepository.remover(estadoId);
//
//        } catch (EmptyResultDataAccessException e) {
//            throw new EntidadeNaoEncontradaException(
//                    String.format("Não existe um cadastro de estado de código %d", estadoId));
//
//        } catch (DataIntegrityViolationException e) {
//            throw new EntidadeEmUsoException(
//                    String.format("Estado de código %d não pode ser removido, pois está em uso", estadoId));
//        }
//    }
}
