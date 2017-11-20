package co.edu.poli.sistemasdistribuidos.votaciones.repositories;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.EleccionEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EleccionRepository extends JpaRepository<EleccionEntity, Long> {

    @Query("select case when (count(v) > 0) then true else false end from EleccionEntity e inner join e.votos v where" +
            " v.usuario = :usuario and e = :eleccion")
    boolean usuarioHaVotadoEnEleccion(@Param("usuario") UsuarioEntity usuario,
                                      @Param("eleccion") EleccionEntity eleccion);
}
