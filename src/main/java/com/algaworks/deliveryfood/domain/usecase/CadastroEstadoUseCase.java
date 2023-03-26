package com.algaworks.deliveryfood.domain.usecase;

import com.algaworks.deliveryfood.domain.exception.EntidadeEmUsoException;
import com.algaworks.deliveryfood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.deliveryfood.domain.model.Estado;
import com.algaworks.deliveryfood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoUseCase {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Estado buscarOuRetornarException(Long estadoId) {
       return estadoRepository.
                findById(estadoId)
                .orElseThrow(() ->
                        new EstadoNaoEncontradoException(
                                String.format("Estado de código %d não encontrado.", estadoId)));
    }

    public void excluir(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException(estadoId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("MSG_ESTADO_EM_USO %d", estadoId));
        }
    }

}
