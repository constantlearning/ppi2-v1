package com.unitri.ppi2v1.repository;

import com.unitri.ppi2v1.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCpf(String cpf);

    @Query(value = "SELECT cliente.id, count(locacao.id)" +
            "       FROM cliente" +
            "       JOIN locacao ON cliente.id = locacao.cliente_id" +
            "       WHERE extract(month from locacao.data) = ?1" +
            "       GROUP BY cliente.id" +
            "       ORDER BY 2 DESC" +
            "       LIMIT 1",
            nativeQuery = true)
    List<Object[]> findAllThatMostHaveRentByMonth(Long mes);
}
