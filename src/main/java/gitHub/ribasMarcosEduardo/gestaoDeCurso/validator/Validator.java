package gitHub.ribasMarcosEduardo.gestaoDeCurso.validator;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Curso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.PessoaEndereco;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Situacao;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.CursoRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.EnderecoRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.PessoaRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.EnderecoService;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.exeption.CursoInativo;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.exeption.CursoNaoencontrado;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.exeption.EnderecoJaCadastradoException;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.exeption.PessoaNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Validator {

    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;
    private final CursoRepository cursoRepository;

    // Validação de Endereço

    public void validarEndereco(PessoaEndereco endereco) {
        if (endereco.getPessoa() == null) {
            throw new PessoaNaoEncontradaException("Pessoa não encontrada.");
        }

        Optional<PessoaEndereco> enderecoExistente = enderecoRepository.findByPessoa_Id(endereco.getPessoa().getId());
        if (enderecoExistente.isPresent()) {
            throw new EnderecoJaCadastradoException("A pessoa já possui um endereço cadastrado.");
        }
    }

    // Validação de curso

    public void validarCurso(Curso curso) {
        if (curso == null || curso.getId() == null) {
            throw new CursoNaoencontrado("O curso não foi encontrado.");
        }

        if (curso.getSituacao().equals(Situacao.Inativo)) {
            throw new CursoInativo("O curso inativo.");
        }
    }
}





//Situacao.Inativo