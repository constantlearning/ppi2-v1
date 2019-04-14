package com.unitri.ppi2v1.resource;

import com.unitri.ppi2v1.domain.Multa;
import com.unitri.ppi2v1.service.MultaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/multas")
public class MultaResource {

    private final MultaService multaService;

    public MultaResource(MultaService multaService) {
        this.multaService = multaService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Multa> findAll() {
        return this.multaService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Multa findById(@PathVariable("id") Long id) {
        return this.multaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Multa create(@Valid @RequestBody Multa multa) {
        return this.multaService.save(multa);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Multa update(@PathVariable("id") Long id, @Valid @RequestBody Multa multa) {
        multa.setId(id);
        return this.multaService.save(multa);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        this.multaService.deleteById(id);
    }
}
