package com.unitri.ppi2v1.service;

import com.unitri.ppi2v1.domain.Categoria;
import com.unitri.ppi2v1.repository.CategoriaRepository;
import com.unitri.ppi2v1.service.exception.CategoriaAlreadyExistException;
import com.unitri.ppi2v1.service.exception.CategoriaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    private Categoria createCategoria(Categoria categoria) {
        verifyIfCategoriaExists(categoria);
        return this.categoriaRepository.save(categoria);
    }

    private void deleteCategoriaById(Long id) {
        Optional<Categoria> categoriaById = this.categoriaRepository.findById(id);
        Categoria categoriaToDelete = categoriaById.orElseThrow(CategoriaNotFoundException::new);
        this.categoriaRepository.delete(categoriaToDelete);
    }

    private Categoria findCategoriaById(Long id) {
        return this.categoriaRepository.findById(id).orElseThrow(CategoriaNotFoundException::new);
    }

    private List<Categoria> findAllCategorias() {
        return this.categoriaRepository.findAll();
    }

    private void verifyIfCategoriaExists(Categoria categoria) {
        Optional<Categoria> byDescricao = this.categoriaRepository.findByDescricao(categoria.getDescricao());
        if (byDescricao.isPresent() && (categoria.isNew() || isUpdatingToADifferentCategoria(categoria, byDescricao))) {
            throw new CategoriaAlreadyExistException();
        }
    }

    private boolean isUpdatingToADifferentCategoria(Categoria categoria, Optional<Categoria> byDescricao) {
        return categoria.isUpdate() && !categoria.getId().equals(byDescricao.get().getId());
    }


}
