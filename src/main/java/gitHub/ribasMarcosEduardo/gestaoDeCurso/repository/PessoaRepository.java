package gitHub.ribasMarcosEduardo.gestaoDeCurso.repository;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
