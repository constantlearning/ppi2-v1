package com.unitri.ppi2v1.service;

import com.unitri.ppi2v1.domain.Avaria;
import com.unitri.ppi2v1.repository.AvariaRepository;
import com.unitri.ppi2v1.service.exception.AvariaAlreadyExistsException;
import com.unitri.ppi2v1.service.exception.AvariaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvariaService {

    private AvariaRepository avariaRepository;

    @Autowired
    public AvariaService(AvariaRepository avariaRepository) {
        this.avariaRepository = avariaRepository;
    }

    public Avaria createAvaria(Avaria avaria) {
        verifyIfAvariaExists(avaria);
        return this.avariaRepository.save(avaria);
    }

    public Avaria findById(Long id) {
        return this.avariaRepository.findById(id).orElseThrow(AvariaNotFoundException::new);
    }

    public List<Avaria> findAllAvarias() {
        return this.avariaRepository.findAll();
    }

    public void deleteAvaria(Long id) {
        Optional<Avaria> avariaById = this.avariaRepository.findById(id);
        Avaria avariaToDelete = avariaById.orElseThrow(AvariaNotFoundException::new);
        this.avariaRepository.delete(avariaToDelete);
    }

    private void verifyIfAvariaExists(Avaria avaria) {
        Optional<Avaria> byDescricaoAndValor = this.avariaRepository.findByDescricaoAndValor(avaria.getDescricao(), avaria.getValor());
        if (byDescricaoAndValor.isPresent() && (avaria.isNew() || isUpdatingToADifferentAvaria(avaria, byDescricaoAndValor))) {
            throw new AvariaAlreadyExistsException();
        }
    }

    private boolean isUpdatingToADifferentAvaria(Avaria avaria, Optional<Avaria> byDescricaoAndValor) {
        return avaria.isUpdate() && !avaria.getId().equals(byDescricaoAndValor.get().getId());
    }

}
