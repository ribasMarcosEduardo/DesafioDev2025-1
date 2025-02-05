package gitHub.ribasMarcosEduardo.gestaoDeCurso.repository;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.EstCursoDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.EstudanteCurso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.PessoaEndereco;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.ProfessorCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EstudanteCurRepository extends JpaRepository<EstudanteCurso, Integer> {
    boolean existsByEstudante_Id(int estudanteId);

    boolean existsByCurso_Id(int cursoId);
}


