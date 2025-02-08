package gitHub.ribasMarcosEduardo.gestaoDeCurso.repository;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.EstCursoDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EstudanteCurRepository extends JpaRepository<EstudanteCurso, Integer> {
    boolean existsByEstudante_Id(int estudanteId);

    Optional<EstudanteCurso> findByEstudanteIdAndCursoId(int estudanteId, int cursoId);

    boolean existsByCurso_Id(int cursoId);

    @Query("SELECT p.nome FROM EstudanteCurso ec " +
            "JOIN ec.estudante p " +
            "JOIN ec.curso c " +
            "WHERE c.nome = :nomeCurso " +
            "ORDER BY p.nome")
    List<String> findNomesEstudantesByCursoNome(@Param("nomeCurso") String nomeCurso);


    @Query("SELECT c.nome FROM EstudanteCurso ec " +
            "JOIN ec.curso c " +
            "JOIN ec.estudante p " +
            "WHERE p.nome = :nomePessoa " +
            "ORDER BY c.nome")
    List<String> findCursosByPessoaNome(@Param("nomePessoa") String nomePessoa);





}


