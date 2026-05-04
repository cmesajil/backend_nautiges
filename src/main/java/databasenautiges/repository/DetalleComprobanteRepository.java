package databasenautiges.repository;

import databasenautiges.model.DetalleComprobante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleComprobanteRepository
    extends JpaRepository<DetalleComprobante, Long> {}
