package databasenautiges.repository;

import databasenautiges.model.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComprobanteRepository
    extends JpaRepository<Comprobante, Long> {}
