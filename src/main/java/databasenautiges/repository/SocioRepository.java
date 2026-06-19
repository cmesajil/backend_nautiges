package databasenautiges.repository;

import databasenautiges.model.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface SocioRepository extends JpaRepository<Socio, Long> {
    
    @Query("SELECT s.estadoSocio, COUNT(s) FROM Socio s GROUP BY s.estadoSocio")
    List<Object[]> countSociosByEstado();
}
