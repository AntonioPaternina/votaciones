package co.edu.poli.sistemasdistribuidos.votaciones.repositories;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.EleccionEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface EleccionRepository extends JpaRepository<EleccionEntity, Long> {

    @Query("select case when (count(v) > 0) then true else false end from EleccionEntity e inner join e.votos v where" +
            " v.usuario = :usuario and e = :eleccion")
    boolean usuarioHaVotadoEnEleccion(@Param("usuario") UsuarioEntity usuario,
                                      @Param("eleccion") EleccionEntity eleccion);

    @Query(value = "select cc.id\n" +
            "from eleccion ee\n" +
            "inner join voto vv on vv.eleccion_id = ee.id\n" +
            "inner join candidato cc on cc.id = vv.candidato_id\n" +
            "where ee.id = :idEleccion\n" +
            "group by cc.id, ee.id\n" +
            "having count(*) = (\n" +
            "select max(totales.conteo)\n" +
            "from \n" +
            "(select count(*) conteo\n" +
            "from eleccion e\n" +
            "inner join voto v on v.eleccion_id = e.id\n" +
            "inner join candidato c on c.id = v.candidato_id\n" +
            "where e.id = :idEleccion\n" +
            "group by candidato_id, e.id) totales\n" +
            ")\n" +
            ";", nativeQuery = true)
    List<BigInteger> calcularGanadoresEleccion(@Param("idEleccion") long idEleccion);

    @Query("select count(v) from VotoEntity v where v.eleccion = :eleccion")
    long obtenerConteoDeVotosPorEleccion(@Param("eleccion") EleccionEntity eleccion);
}
