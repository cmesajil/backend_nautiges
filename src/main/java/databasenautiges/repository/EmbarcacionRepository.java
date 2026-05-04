package databasenautiges.repository;

import databasenautiges.model.Embarcacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmbarcacionRepository
    extends JpaRepository<Embarcacion, Long> {}
