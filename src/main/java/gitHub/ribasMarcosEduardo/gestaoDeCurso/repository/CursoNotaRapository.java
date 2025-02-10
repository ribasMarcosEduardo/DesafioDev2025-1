package gitHub.ribasMarcosEduardo.gestaoDeCurso.repository;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.CursoNota;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.EstudanteCurso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.TipNota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CursoNotaRapository extends JpaRepository<CursoNota, Integer> {
    List<EstudanteCurso> findByCursoId(Integer cursoId);

    @Query("SELECT cn.valor FROM CursoNota cn WHERE cn.estudante.id = :idEstudante AND cn.tipNota = :tipNota")
    Optional<Double> findNotaByEstudanteAndTipo(@Param("idEstudante") int idEstudante, @Param("tipNota") TipNota tipNota);

    List<CursoNota> findByEstudanteCursoId(int estudanteCursoId);

    List<CursoNota> findByTipNota(TipNota tipNota);

}