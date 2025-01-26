package gitHub.ribasMarcosEduardo.gestaoDeCurso.service;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.PessoaEndereco;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.EnderecoRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.PessoaRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.exeption.PessoaNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;

    public Pessoa buscarPessoa(int CODPESSOA) {
        return pessoaRepository.findById(CODPESSOA)
                .orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa com ID " + CODPESSOA + " não encontrada!"));
    }

    @Transactional
    public PessoaEndereco salvarEndereco(PessoaEndereco pessoaEndereco) {
        return enderecoRepository.save(pessoaEndereco);
    }
}