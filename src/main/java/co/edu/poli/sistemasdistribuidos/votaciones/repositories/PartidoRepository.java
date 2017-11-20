package co.edu.poli.sistemasdistribuidos.votaciones.repositories;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.PartidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends JpaRepository<PartidoEntity, Long> {
    PartidoEntity findByNombre(String nombre);
}
