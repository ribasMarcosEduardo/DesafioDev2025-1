package gitHub.ribasMarcosEduardo.gestaoDeCurso.repository;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.EstudanteCurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudanteCurRepository extends JpaRepository<EstudanteCurso, Integer> {

    //Optional<EstudanteCurso> findByPessoa_IdAndCurso_Id(int codPessoa, int codCurso);

}
