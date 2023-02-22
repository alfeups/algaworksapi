package com.algaworks.deliveryfood.domain.usecase;

import com.algaworks.deliveryfood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.deliveryfood.domain.exception.EntidadeEmUsoException;
import com.algaworks.deliveryfood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.deliveryfood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.deliveryfood.domain.model.Cidade;
import com.algaworks.deliveryfood.domain.model.Estado;
import com.algaworks.deliveryfood.domain.repository.CidadeRepository;
import com.algaworks.deliveryfood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeUseCase {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        var estado = estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
        if (estado == null) {
            throw new EstadoNaoEncontradoException(estadoId);
        }
        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }


    public void excluir(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);
        } catch (CidadeNaoEncontradaException e) {
            throw new CidadeNaoEncontradaException(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cidade de código %d não pode ser removida, pois está em uso", cidadeId));
        }
    }

}