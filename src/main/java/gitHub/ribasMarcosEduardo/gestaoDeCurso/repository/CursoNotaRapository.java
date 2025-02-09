package gitHub.ribasMarcosEduardo.gestaoDeCurso.repository;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.CursoNota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoNotaRapository extends JpaRepository<CursoNota, Integer> {
    //List<CursoNota> findByCursoNotaIdAndTipNota(int cursoNotaId, TipNota tipoNota);
}
