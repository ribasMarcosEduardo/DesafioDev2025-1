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

    @Query("SELECT p.id, p.nome, p.cpf, p.telefone, p.email, p.ativo, p.role " +
            "FROM EstudanteCurso ec " +
            "JOIN ec.estudante p " +
            "JOIN ec.curso c " +
            "WHERE c.nome = :nomeCurso " +
            "ORDER BY p.nome")
    List<Object[]> findEstudantesByCursoNome(@Param("nomeCurso") String nomeCurso);

    @Query("SELECT c.nome, c.assunto, c.encontros, c.situacao FROM EstudanteCurso ec " +
            "JOIN ec.curso c " +
            "JOIN ec.estudante p " +
            "WHERE p.nome = :nomePessoa " +
            "ORDER BY c.nome")
    List<Object[]> findCursosByPessoaNome(@Param("nomePessoa") String nomePessoa);

    @Query("SELECT COUNT(ec) FROM EstudanteCurso ec WHERE ec.curso.id = :cursoId")
    int countEstudantesByCursoId(@Param("cursoId") int cursoId);

    @Query("SELECT e FROM EstudanteCurso e WHERE e.curso.nome = :nome ORDER BY e.estudante.nome ASC")
    List<EstudanteCurso> findEstudantesComCodigoPorCursoNome(@Param("nome") String nome);




}


