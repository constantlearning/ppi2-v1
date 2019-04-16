package com.unitri.ppi2v1.repository;

import com.unitri.ppi2v1.domain.Cliente;
import com.unitri.ppi2v1.domain.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

    Optional<Locacao> findByCliente(Cliente cliente);

    Optional<Locacao> findByClienteAndData(Cliente cliente, LocalDate data);

    @Query(value =
            "SELECT loc.id, count(loc_m.locacao_id) " +
                    "FROM locacao loc " +
                    "JOIN locacao_multas loc_m ON loc_m .locacao_id = loc.id " +
                    "GROUP BY loc_m.locacao_id " +
                    "ORDER BY 2 DESC",
            nativeQuery = true)
    List<Object[]> findByMultasOrderedByParameterDesc(String parameter);

    @Query(value =
            "SELECT loc.id, count(loc_m.locacao_id) " +
                    "FROM locacao loc " +
                    "JOIN locacao_multas loc_m ON loc_m .locacao_id = loc.id " +
                    "GROUP BY loc_m.locacao_id " +
                    "ORDER BY 2 ASC",
            nativeQuery = true)
    List<Object[]> findByMultasOrderedByParameterAsc(String parameter);

    @Query(value =
            "SELECT loc.id " +
                    "FROM locacao loc " +
                    "WHERE extract(month from loc.data) = ?1",
            nativeQuery = true)
    List<Object[]> findByMonth(Long month);
}
