package com.unitri.ppi2v1.resource;

import com.unitri.ppi2v1.domain.Navio;
import com.unitri.ppi2v1.service.NavioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/navios")
public class NavioResource {

    private final NavioService navioService;

    public NavioResource(NavioService navioService) {
        this.navioService = navioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Navio create(@Valid @RequestBody Navio navio) {
        return this.navioService.save(navio);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Navio update(@PathVariable(name = "id") Long id, @Valid @RequestBody Navio navio) {
        navio.setId(id);
        return this.navioService.save(navio);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") Long id) {
        this.navioService.deleteById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Navio> findNavios() {
        return this.navioService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Navio findById(@PathVariable(name = "id") Long id) {
        return this.navioService.findById(id);
    }
}
