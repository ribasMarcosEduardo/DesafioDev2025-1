package gitHub.ribasMarcosEduardo.gestaoDeCurso.repository;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.CursoPresenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CursoPresencaRepository extends JpaRepository<CursoPresenca, Integer> {

    @Query("SELECT COUNT(cp) FROM CursoPresenca cp WHERE cp.curso.id = :cursoId")
    long countPresencasByCursoId(@Param("cursoId") int cursoId);

}
