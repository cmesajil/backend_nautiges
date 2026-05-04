package databasenautiges.repository;

import databasenautiges.model.SolicitudSalida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudSalidaRepository
    extends JpaRepository<SolicitudSalida, Long> {}
