package gitHub.ribasMarcosEduardo.gestaoDeCurso.repository;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.PessoaEndereco;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.ProfessorCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<ProfessorCurso, Integer> {

    Optional<ProfessorCurso> findByCursoId(int cursoId);
    @Query("SELECT p.professor.id FROM ProfessorCurso p WHERE p.professor.id = :pessoaId")
    Optional<Integer> findCodPessoaById(@Param("pessoaId") int pessoaId);



}



