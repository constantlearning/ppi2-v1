package com.unitri.ppi2v1.service;

import com.unitri.ppi2v1.domain.Locacao;
import com.unitri.ppi2v1.repository.LocacaoRepository;
import com.unitri.ppi2v1.service.exception.LocacaoAlreadyExistException;
import com.unitri.ppi2v1.service.exception.LocacaoNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

    public Page<Locacao> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return this.locacaoRepository.findAll(pageRequest);
    }

    private void verifyIfLocacaoExists(final Locacao locacao) {
        Optional<Locacao> locacaoByCliente = this.locacaoRepository.findByClienteAndData(locacao.getCliente(), locacao.getData());
        if (locacaoByCliente.isPresent() && (locacao.isNew() || isUpdatingToADiffentLocacao(locacao, locacaoByCliente.get()))) {
            throw new LocacaoAlreadyExistException();
        }
    }

    private boolean isUpdatingToADiffentLocacao(Locacao locacao, Locacao locacaoByCliente) {
        return locacao.isUpdate() && !locacaoByCliente.getId().equals(locacao.getId());
    }

    public List<Locacao> findLocacoesOrderByParameter(String parameter) {

        List<Object[]> byMultasOrderedByParameter;

        if (parameter.equals("ASC")) {
            byMultasOrderedByParameter = this.locacaoRepository.findByMultasOrderedByParameterAsc(parameter);
        } else {
            byMultasOrderedByParameter = this.locacaoRepository.findByMultasOrderedByParameterDesc(parameter);
        }

        if (byMultasOrderedByParameter.isEmpty()) {
            return Collections.emptyList();
        }

        return mapLocationsByIds(byMultasOrderedByParameter);
    }

    public List<Locacao> mapLocationsByIds(List<Object[]> ids) {
        List<Locacao> locacoes = new ArrayList<>();

        for (Object[] result : ids) {
            Long locacaoId = Long.valueOf(result[0].toString());
            Optional<Locacao> byId = this.locacaoRepository.findById(locacaoId);
            byId.ifPresent(locacoes::add);
        }

        return locacoes;
    }

    public List<Locacao> findLocationsByMonth(Long month) {
        List<Object[]> result = this.locacaoRepository.findByMonth(month);
        return mapLocationsByIds(result);
    }
}
