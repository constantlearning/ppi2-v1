package com.unitri.ppi2v1.resource;

import com.unitri.ppi2v1.domain.Categoria;
import com.unitri.ppi2v1.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    private final CategoriaService categoriaService;

    public CategoriaResource(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Categoria> findCategorias() {
        return this.categoriaService.findAllCategorias();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Categoria findById(@PathVariable(name = "id") Long id) {
        return this.categoriaService.findCategoriaById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria create(@Valid @RequestBody Categoria categoria) {
        return this.categoriaService.save(categoria);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Categoria update(@PathVariable(name = "id") Long id, @Valid @RequestBody Categoria categoria) {
        categoria.setId(id);
        return this.categoriaService.save(categoria);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") Long id) {
        this.categoriaService.deleteCategoriaById(id);
    }
}
