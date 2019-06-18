package com.unitri.ppi2v1.service;

import com.unitri.ppi2v1.domain.Categoria;
import com.unitri.ppi2v1.domain.Navio;
import com.unitri.ppi2v1.repository.NavioRepository;
import com.unitri.ppi2v1.service.exception.VeiculoAlreadyExistsException;
import com.unitri.ppi2v1.service.exception.VeiculoNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NavioService {

    private final NavioRepository navioRepository;
    private final CategoriaService categoriaService;

    public NavioService(NavioRepository navioRepository, CategoriaService categoriaService) {
        this.navioRepository = navioRepository;
        this.categoriaService = categoriaService;
    }

    public Navio save(Navio navio) {
        verifyIfVeiculoExists(navio);
        return this.navioRepository.save(navio);
    }

    public void deleteById(Long id) {
        Optional<Navio> byId = this.navioRepository.findById(id);
        Navio navioToDelete = byId.orElseThrow(VeiculoNotFoundException::new);
        this.navioRepository.delete(navioToDelete);
    }

    public Navio findById(Long id) {
        return this.navioRepository.findById(id).orElseThrow(VeiculoNotFoundException::new);
    }

    public List<Navio> findAll() {
        return this.navioRepository.findAll();
    }

    private void verifyIfVeiculoExists(Navio navio) {
        Optional<Navio> byMarcaAndModelo = this.navioRepository.findByModelo(navio.getModelo());
        if (byMarcaAndModelo.isPresent() && (navio.isNew() || isUpdatingToADifferentVeiculo(navio, byMarcaAndModelo.get()))) {
            throw new VeiculoAlreadyExistsException();
        }
    }

    private boolean isUpdatingToADifferentVeiculo(Navio veiculo, Navio byMarcaAndModelo) {
        return veiculo.isUpdate() && !veiculo.getId().equals(byMarcaAndModelo.getId());
    }

}
