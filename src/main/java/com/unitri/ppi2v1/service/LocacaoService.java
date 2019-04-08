package com.unitri.ppi2v1.service;

import com.unitri.ppi2v1.domain.Locacao;
import com.unitri.ppi2v1.repository.LocacaoRepository;
import com.unitri.ppi2v1.service.exception.LocacaoAlreadyExistException;
import com.unitri.ppi2v1.service.exception.LocacaoNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocacaoService {

    private final LocacaoRepository locacaoRepository;

    public LocacaoService(LocacaoRepository locacaoRepository) {
        this.locacaoRepository = locacaoRepository;
    }

    public Locacao save(Locacao locacao) {
        verifyIfLocacaoExists(locacao);
        return this.locacaoRepository.save(locacao);
    }

    public void deleteById(Long id) {
        Optional<Locacao> locacaoById = this.locacaoRepository.findById(id);
        Locacao locacaoToDelete = locacaoById.orElseThrow(LocacaoNotFoundException::new);
        this.locacaoRepository.delete(locacaoToDelete);
    }

    public Locacao findById(Long id) {
        return this.locacaoRepository.findById(id).orElseThrow(LocacaoNotFoundException::new);
    }

    public List<Locacao> findAll() {
        return this.locacaoRepository.findAll();
    }

    private void verifyIfLocacaoExists(final Locacao locacao) {
        Optional<Locacao> locacaoByCliente = this.locacaoRepository.findByCliente(locacao.getCliente());
        if (locacaoByCliente.isPresent() && (locacao.isNew() || isUpdatingToADiffentLocacao(locacao, locacaoByCliente.get()))) {
            throw new LocacaoAlreadyExistException();
        }
    }

    private boolean isUpdatingToADiffentLocacao(Locacao locacao, Locacao locacaoByCliente) {
        return locacao.isUpdate() && !locacaoByCliente.getId().equals(locacao.getId());
    }
}
