package gitHub.ribasMarcosEduardo.gestaoDeCurso.service;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;

    public Pessoa salvarProduto(Pessoa pessoa) {
        return repository.save(pessoa);
    }
}
