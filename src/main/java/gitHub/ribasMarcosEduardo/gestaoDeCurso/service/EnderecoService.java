package gitHub.ribasMarcosEduardo.gestaoDeCurso.service;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.PessoaEndereco;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.EnderecoRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final Validator validator;

    @Transactional
    public PessoaEndereco salvarEndereco(PessoaEndereco pessoaEndereco) {
        validator.validarPessoa(pessoaEndereco.getPessoa()); // dar uma olhada nq deu com essa validation e finalizar testes
        validator.validarEndereco(pessoaEndereco);
        return enderecoRepository.save(pessoaEndereco);
    }
}