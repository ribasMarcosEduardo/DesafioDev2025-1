package gitHub.ribasMarcosEduardo.gestaoDeCurso.validator;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.*;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.CursoRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.EnderecoRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.PessoaRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.ProfessorRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.exeption.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Validator {

    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;
    private final CursoRepository cursoRepository;
    private final ProfessorRepository professorRepository;

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

    // Validação de Pessoa

    public void validarPessoa(Pessoa pessoa){
        if (pessoa == null) {
            throw new PessoaNaoEncontradaException("Pessoa não encontrada.");
        }

        if (!pessoa.isAtivo()) {
            throw new PessoaInativa("Pessoa inativo.");
        }
    }

    // Validação de professor

    public void validarOferta(ProfessorCurso professor) {

        Optional<ProfessorCurso> ofertaExistente = professorRepository.findByCursoId(professor.getCurso().getId());
        if (ofertaExistente.isPresent()) {
            throw new ProfessorDuplicado("Essa oferta já tem um professor vinculado!");
        }


    }



}








