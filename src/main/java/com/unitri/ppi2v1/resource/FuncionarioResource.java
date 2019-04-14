package com.unitri.ppi2v1.resource;

import com.unitri.ppi2v1.domain.Funcionario;
import com.unitri.ppi2v1.service.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioResource {

    private final FuncionarioService funcionarioService;

    public FuncionarioResource(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Funcionario> findAll() {
        return this.funcionarioService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Funcionario findById(@PathVariable("id") Long id) {
        return this.funcionarioService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario create(@Valid @RequestBody Funcionario funcionario) {
        return this.funcionarioService.save(funcionario);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Funcionario update(@PathVariable("id") Long id, @Valid @RequestBody Funcionario funcionario) {
        funcionario.setId(id);
        return this.funcionarioService.save(funcionario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        this.funcionarioService.deleteById(id);
    }
}
