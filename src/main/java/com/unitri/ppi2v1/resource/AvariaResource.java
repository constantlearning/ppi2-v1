package com.unitri.ppi2v1.resource;

import com.unitri.ppi2v1.domain.Avaria;
import com.unitri.ppi2v1.service.AvariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/avarias")
public class AvariaResource {

    private final AvariaService avariaService;

    public AvariaResource(AvariaService avariaService) {
        this.avariaService = avariaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Avaria create(@Valid @RequestBody Avaria avaria) {
        return this.avariaService.save(avaria);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Avaria update(@PathVariable(name = "id") Long id, @Valid @RequestBody Avaria avaria) {
        avaria.setId(id);
        return this.avariaService.save(avaria);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") Long id) {
        this.avariaService.deleteAvaria(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Avaria> findAvarias() {
        return this.avariaService.findAllAvarias();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Avaria findById(@PathVariable(name = "id") Long id) {
        return this.avariaService.findById(id);
    }
}
