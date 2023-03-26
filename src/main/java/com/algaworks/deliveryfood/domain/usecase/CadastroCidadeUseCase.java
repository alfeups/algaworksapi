package com.algaworks.deliveryfood.domain.usecase;

import com.algaworks.deliveryfood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.deliveryfood.domain.exception.EntidadeEmUsoException;
import com.algaworks.deliveryfood.domain.model.Cidade;
import com.algaworks.deliveryfood.domain.repository.CidadeRepository;
import com.algaworks.deliveryfood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroCidadeUseCase {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade buscarOuFalhar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
    }

    public Cidade salvar(Cidade cidade) {
        return cidadeRepository.
                findById(cidade.getId())
                .orElseThrow(() ->
                        new CidadeNaoEncontradaException(
                                String.format("Cidade de código %d não encontrada.", cidade.getId())));
    }

    public void excluir(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);
        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(cidadeId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("MSG_CIDADE_EM_USO %d", cidadeId));
        }
    }

}