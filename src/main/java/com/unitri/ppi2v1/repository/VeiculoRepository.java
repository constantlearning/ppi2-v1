package com.unitri.ppi2v1.repository;

import com.unitri.ppi2v1.domain.Categoria;
import com.unitri.ppi2v1.domain.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    Optional<Veiculo> findByMarcaAndModelo(String marca, String modelo);

    List<Veiculo> findAllByCategoria(Categoria categoria);
}
