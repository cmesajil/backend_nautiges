package databasenautiges.repository;

import databasenautiges.model.SolicitudInscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudInscripcionRepository
    extends JpaRepository<SolicitudInscripcion, Long> {}
