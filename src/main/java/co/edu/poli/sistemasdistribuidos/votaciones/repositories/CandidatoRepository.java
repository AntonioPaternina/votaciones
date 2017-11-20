package co.edu.poli.sistemasdistribuidos.votaciones.repositories;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.CandidatoEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.EleccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatoRepository extends JpaRepository<CandidatoEntity, Long> {

    @Query("select c from CandidatoEntity c inner join c.elecciones e where e = :eleccion")
    List<CandidatoEntity> findByEleccion(@Param("eleccion") EleccionEntity eleccion);

    @Query("select count(v) from VotoEntity v inner join v.candidato c inner join v.eleccion e where c = :candidato " +
            "and e = :eleccion")
    long obtenerConteoVotosDelCandidatoParaEleccion(@Param("candidato") CandidatoEntity candidato,
                                                    @Param("eleccion") EleccionEntity eleccion);
}
