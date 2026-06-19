package databasenautiges.repository;

import databasenautiges.model.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ComprobanteRepository extends JpaRepository<Comprobante, Long> {

    @Query("SELECT YEAR(c.fechaEmision), MONTH(c.fechaEmision), SUM(c.total) FROM Comprobante c GROUP BY YEAR(c.fechaEmision), MONTH(c.fechaEmision) ORDER BY YEAR(c.fechaEmision), MONTH(c.fechaEmision)")
    List<Object[]> sumTotalByMonth();
}
