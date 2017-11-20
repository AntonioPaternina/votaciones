package co.edu.poli.sistemasdistribuidos.votaciones.repositories;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.VotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<VotoEntity, Long> {

}
