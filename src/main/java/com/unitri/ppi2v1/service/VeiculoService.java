package com.unitri.ppi2v1.service;

import com.unitri.ppi2v1.domain.Categoria;
import com.unitri.ppi2v1.domain.Locacao;
import com.unitri.ppi2v1.domain.Veiculo;
import com.unitri.ppi2v1.repository.LocacaoRepository;
import com.unitri.ppi2v1.repository.VeiculoRepository;
import com.unitri.ppi2v1.service.exception.VeiculoAlreadyExistsException;
import com.unitri.ppi2v1.service.exception.VeiculoNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final CategoriaService categoriaService;
    private final LocacaoRepository locacaoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository, CategoriaService categoriaService, LocacaoRepository locacaoRepository) {
        this.veiculoRepository = veiculoRepository;
        this.categoriaService = categoriaService;
        this.locacaoRepository = locacaoRepository;
    }

    public Veiculo save(Veiculo veiculo) {
        verifyIfVeiculoExists(veiculo);
        return this.veiculoRepository.save(veiculo);
    }

    public void deleteById(Long id) {
        Optional<Veiculo> byId = this.veiculoRepository.findById(id);
        Veiculo veiculoToDelete = byId.orElseThrow(VeiculoNotFoundException::new);
        this.veiculoRepository.delete(veiculoToDelete);
    }

    public Veiculo findById(Long id) {
        return this.veiculoRepository.findById(id).orElseThrow(VeiculoNotFoundException::new);
    }

    public List<Veiculo> findAll() {
        return this.veiculoRepository.findAll();
    }

    private void verifyIfVeiculoExists(Veiculo veiculo) {
        Optional<Veiculo> byMarcaAndModelo = this.veiculoRepository.findByMarcaAndModelo(veiculo.getMarca(), veiculo.getModelo());
        if (byMarcaAndModelo.isPresent() && (veiculo.isNew() || isUpdatingToADifferentVeiculo(veiculo, byMarcaAndModelo.get()))) {
            throw new VeiculoAlreadyExistsException();
        }
    }

    private boolean isUpdatingToADifferentVeiculo(Veiculo veiculo, Veiculo byMarcaAndModelo) {
        return veiculo.isUpdate() && !veiculo.getId().equals(byMarcaAndModelo.getId());
    }

    public List<Veiculo> findByCategoriaId(Long categoriaId) {
        Categoria categoria = this.categoriaService.findCategoriaById(categoriaId);
        return this.veiculoRepository.findAllByCategoria(categoria);
    }

    public List<Locacao> findVehicleLocations(Long vehicleId) {

        List<Object[]> locationsIds = this.veiculoRepository.findLocationByVehicle(vehicleId);

        List<Locacao> locacoes = new ArrayList<>();

        for (Object[] result : locationsIds) {
            Long locacaoId = Long.valueOf(result[0].toString());
            Optional<Locacao> byId = this.locacaoRepository.findById(locacaoId);
            byId.ifPresent(locacoes::add);
        }

        return locacoes;
    }
}
