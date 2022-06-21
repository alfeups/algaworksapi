package com.algaworks.deliveryfood.domain.usecase;

import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeUseCase {

//    @Autowired
//    private CidadeRepository cidadeRepository;
//
//    @Autowired
//    private EstadoRepository estadoRepository;
//
//    public Cidade salvar(Cidade cidade) {
//        Long estadoId = cidade.getEstado().getId();
//        Estado estado = estadoRepository.buscar(estadoId);
//
//        if (estado == null) {
//            throw new EntidadeNaoEncontradaException(
//                    String.format("Não existe cadastro de estado com código %d", estadoId));
//        }
//        cidade.setEstado(estado);
//
//        return cidadeRepository.adicionarCidade(cidade);
//    }
//
//
//    public void excluir(Long cidadeId) {
//        try {
//            cidadeRepository.removerCidade(cidadeId);
//
//        } catch (EmptyResultDataAccessException e) {
//            throw new EntidadeNaoEncontradaException(
//                    String.format("Não existe um cadastro de cidade com código %d", cidadeId));
//
//        } catch (DataIntegrityViolationException e) {
//            throw new EntidadeEmUsoException(
//                    String.format("Cidade de código %d não pode ser removida, pois está em uso", cidadeId));
//        }
//    }

}