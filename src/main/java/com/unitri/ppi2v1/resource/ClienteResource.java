package com.unitri.ppi2v1.resource;

import com.unitri.ppi2v1.domain.Cliente;
import com.unitri.ppi2v1.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    private final ClienteService clienteService;

    public ClienteResource(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> findAll() {
        return this.clienteService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente findById(@PathVariable("id") Long id) {
        return this.clienteService.findById(id);
    }

    @GetMapping("/cpf/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente findByCpf(@PathVariable("cpf") String cpf) {
        return this.clienteService.findByCpf(cpf);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente create(@Valid @RequestBody Cliente cliente) {
        return this.clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente update(@PathVariable("id") Long id, @Valid @RequestBody Cliente cliente) {
        cliente.setId(id);
        return this.clienteService.save(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        this.clienteService.deleteById(id);
    }

    @GetMapping("/locacoes/{month}")
    public Cliente maisLocaPorMes(@PathVariable("month") @Min(value = 1) @Max(value = 12) Long month) {
        return this.clienteService.findAllThatMostHaveRentByMonth(month);
    }
}
