package gitHub.ribasMarcosEduardo.gestaoDeCurso.repository;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.PessoaEndereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<PessoaEndereco, Integer> {

}
