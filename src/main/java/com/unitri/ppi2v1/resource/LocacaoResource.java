package com.unitri.ppi2v1.resource;

import com.unitri.ppi2v1.domain.Locacao;
import com.unitri.ppi2v1.service.LocacaoService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/locacoes")
public class LocacaoResource {

    private final LocacaoService locacaoService;

    public enum Ordenacao {
        ASC,
        DESC
    }

    public LocacaoResource(LocacaoService locacaoService) {
        this.locacaoService = locacaoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Locacao> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                 @RequestParam(value = "size", required = false, defaultValue = "2") int size) {
        return this.locacaoService.findAll(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Locacao findById(@PathVariable("id") Long id) {
        return this.locacaoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Locacao create(@Valid @RequestBody Locacao locacao) {
        return this.locacaoService.save(locacao);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Locacao update(@PathVariable("id") Long id, @Valid @RequestBody Locacao locacao) {
        locacao.setId(id);
        return this.locacaoService.save(locacao);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        this.locacaoService.deleteById(id);
    }

    @GetMapping("/multas")
    @ResponseStatus(HttpStatus.OK)
    public List<Locacao> locacoesOrdenadasPeloNumeroDeMultas(@RequestParam("ordenacao") Ordenacao ordenacao) {
        String parameter = ordenacao.name();
        return this.locacaoService.findLocacoesOrderByParameter(parameter);
    }

    @GetMapping("/month/{month}")
    @ResponseStatus(HttpStatus.OK)
    public List<Locacao> findByMonth(@PathVariable("month") @Min(value = 1) @Max(value = 12) Long month) {
        return this.locacaoService.findLocationsByMonth(month);
    }
}
