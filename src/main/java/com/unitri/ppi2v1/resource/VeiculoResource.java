package com.unitri.ppi2v1.resource;

import com.unitri.ppi2v1.domain.Veiculo;
import com.unitri.ppi2v1.service.VeiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoResource {

    private final VeiculoService veiculoService;

    public VeiculoResource(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Veiculo> findAll() {
        return this.veiculoService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Veiculo findById(@PathVariable("id") Long id) {
        return this.veiculoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo create(@Valid @RequestBody Veiculo veiculo) {
        return this.veiculoService.save(veiculo);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Veiculo update(@PathVariable("id") Long id, @Valid @RequestBody Veiculo veiculo) {
        veiculo.setId(id);
        return this.veiculoService.save(veiculo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        this.veiculoService.deleteById(id);
    }
}
