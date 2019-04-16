package com.unitri.ppi2v1.repository;

import com.unitri.ppi2v1.domain.Categoria;
import com.unitri.ppi2v1.domain.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    Optional<Veiculo> findByMarcaAndModelo(String marca, String modelo);

    List<Veiculo> findAllByCategoria(Categoria categoria);

    @Query(
            value = "SELECT loc.id " +
                    "FROM locacao loc " +
                    "JOIN veiculo vei ON loc.veiculo_id = vei.id " +
                    "WHERE loc.veiculo_id = ?1",
            nativeQuery = true
    )
    List<Object[]> findLocationByVehicle(Long vehicleId);
}
