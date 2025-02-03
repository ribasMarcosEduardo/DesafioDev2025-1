package gitHub.ribasMarcosEduardo.gestaoDeCurso.repository;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.EstCursoDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.EstudanteCurso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.PessoaEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EstudanteCurRepository extends JpaRepository<EstudanteCurso, Integer> {

    //Optional<EstudanteCurso> findByPessoa_IdAndCurso_Id(int codPessoa, int codCurso);

    @Query("SELECT e.estudante.id FROM EstudanteCurso e WHERE e.estudante.id = :pessoaId")
    Optional<Integer> findCodPessoaById(@Param("pessoaId") int pessoaId);


}


