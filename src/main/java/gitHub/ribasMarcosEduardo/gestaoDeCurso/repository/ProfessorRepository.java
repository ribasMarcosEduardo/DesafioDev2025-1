package gitHub.ribasMarcosEduardo.gestaoDeCurso.repository;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Curso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.ProfessorCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<ProfessorCurso, Integer> {

    Optional<ProfessorCurso> findByCursoId(int cursoId);

    boolean existsByProfessor_Id(int professorId);

    boolean existsByCurso_Id(int cursoId);

    @Query("SELECT c FROM ProfessorCurso pc JOIN pc.curso c JOIN pc.professor p WHERE p.nome = :nomeProfessor ORDER BY c.nome ASC")
    List<Curso> findCursosByProfessorNome(@Param("nomeProfessor") String nomeProfessor);





}



