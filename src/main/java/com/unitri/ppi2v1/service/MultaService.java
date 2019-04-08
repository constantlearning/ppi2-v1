package com.unitri.ppi2v1.service;

import com.unitri.ppi2v1.domain.Multa;
import com.unitri.ppi2v1.repository.MultaRepository;
import com.unitri.ppi2v1.service.exception.MultaAlreadyExistsException;
import com.unitri.ppi2v1.service.exception.MultaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MultaService {

    private final MultaRepository multaRepository;

    @Autowired
    public MultaService(MultaRepository multaRepository) {
        this.multaRepository = multaRepository;
    }

    public Multa save(Multa multa) {
        verifyIfMultaExists(multa);
        return this.multaRepository.save(multa);
    }

    public void delete(final Long id) {
        Optional<Multa> byId = this.multaRepository.findById(id);
        Multa multaToDelete = byId.orElseThrow(MultaNotFoundException::new);
        this.multaRepository.delete(multaToDelete);
    }

    public Multa findById(final Long id) {
        return this.multaRepository.findById(id).orElseThrow(MultaNotFoundException::new);
    }

    public List<Multa> findAll() {
        return this.multaRepository.findAll();
    }

    private void verifyIfMultaExists(Multa multa) {

        Optional<Multa> byDescricaoAndValor = this.multaRepository.findByDescricaoAndValor(multa.getDescricao(), multa.getValor());

        if (byDescricaoAndValor.isPresent() && (multa.isNew() || isUpdatingToADifferentMulta(byDescricaoAndValor.get(), multa))) {
            throw new MultaAlreadyExistsException();
        }
    }

    private boolean isUpdatingToADifferentMulta(Multa multaByDescricaoAndValor, Multa multa) {
        return multa.isUpdate() && multa.getId() != multaByDescricaoAndValor.getId();
    }
}
