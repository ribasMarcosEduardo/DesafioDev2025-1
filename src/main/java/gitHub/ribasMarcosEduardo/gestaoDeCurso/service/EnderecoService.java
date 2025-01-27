package gitHub.ribasMarcosEduardo.gestaoDeCurso.service;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.PessoaEndereco;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.EnderecoRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.EnderecoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoValidator enderecoValidator;

    @Transactional
    public PessoaEndereco salvarEndereco(PessoaEndereco pessoaEndereco) {
        enderecoValidator.validar(pessoaEndereco);
        return enderecoRepository.save(pessoaEndereco);
    }
}