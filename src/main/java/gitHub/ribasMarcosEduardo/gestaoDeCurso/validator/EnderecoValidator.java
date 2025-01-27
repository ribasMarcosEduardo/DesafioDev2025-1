package gitHub.ribasMarcosEduardo.gestaoDeCurso.validator;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.PessoaEndereco;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.EnderecoRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.PessoaRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.EnderecoService;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.exeption.EnderecoJaCadastradoException;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.exeption.PessoaNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EnderecoValidator {

    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;

    public void validar(PessoaEndereco endereco) {
        if (endereco.getPessoa() == null) {
            throw new PessoaNaoEncontradaException("Pessoa não encontrada.");
        }

        Optional<PessoaEndereco> enderecoExistente = enderecoRepository.findByPessoa_Id(endereco.getPessoa().getId());
        if (enderecoExistente.isPresent()) {
            throw new EnderecoJaCadastradoException("A pessoa já possui um endereço cadastrado.");
        }
    }



}
