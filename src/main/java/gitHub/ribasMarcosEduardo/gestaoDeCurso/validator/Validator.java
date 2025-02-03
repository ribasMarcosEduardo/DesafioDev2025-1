package gitHub.ribasMarcosEduardo.gestaoDeCurso.validator;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.*;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.*;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.exeption.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Validator {

    private final EnderecoRepository enderecoRepository;
    private final ProfessorRepository professorRepository;
    private final CursoRepository cursoRepository;
    private final PessoaRepository pessoaRepository;
    private final EstudanteCurRepository estudanteCurRepository;

    // Validação de Endereço

    public void validarEndereco(PessoaEndereco endereco) {
        Optional<PessoaEndereco> enderecoExistente = enderecoRepository.findByPessoa_Id(endereco.getPessoa().getId());
        if (enderecoExistente.isPresent()) {
            throw new EnderecoJaCadastradoException("A pessoa já possui um endereço cadastrado.");
        }
    }
    //------------------------------------------------------------------------------------------------------------------

    // Validação de curso

    public void validarCurso(Curso curso) {
        if (curso == null || curso.getId() == null) {
            throw new CursoNaoencontrado("O curso não foi encontrado.");
        }

        if (curso.getSituacao().equals(Situacao.Inativo)) {
            throw new CursoInativo("O curso inativo.");
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    // Validação de Pessoa

    public void validarPessoa(Pessoa pessoa){
        if (pessoa == null) {
            throw new PessoaNaoEncontradaException("Pessoa não encontrada.");
        }

        if (!pessoa.isAtivo()) {
            throw new PessoaInativa("Pessoa inativo.");
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    // Validação de professor

    public void validarOferta(ProfessorCurso professor) {
        Optional<ProfessorCurso> ofertaExistente = professorRepository.findByCursoId(professor.getCurso().getId());
        if (ofertaExistente.isPresent()) {
            throw new ProfessorDuplicado("Essa oferta já tem um professor vinculado!");
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    // Valdação de curso duplicado

    public void cursoDuplicado(Curso curso){
        Optional<Curso> cursoExistente = cursoRepository.findByNome(curso.getNome());
        if(cursoExistente.isPresent()){
            throw new CursoDuplicado("Já existe um curso com o mesmo nome");
        }
    }

    // Validação de Cadastro duplicado

    public void usuarioDuplicado(Pessoa pessoa){
        Optional<Pessoa> usuarioExistente = pessoaRepository.findByUsuario(pessoa.getUsuario());
        if(usuarioExistente.isPresent()){
            throw new UsuarioDuplicado("Já escolheram esse nome!");
        }

        String cpfNormalizado = pessoa.getCpf().replaceAll("\\D", "");
        Optional<Pessoa> cpfExistente = pessoaRepository.findByCpf(cpfNormalizado);
        if (cpfExistente.isPresent()) {
            throw new CpfJaCadastrado("Usuário já cadastrado");
        }
    }

    // Validação tela de busca e edição

    public void cpfExistEdit(Pessoa pessoa) {
        String cpfNormalizado = pessoa.getCpf().replaceAll("\\D", "");
        Optional<Pessoa> cpfExistente = pessoaRepository.findByCpf(cpfNormalizado);
        if (cpfExistente.isPresent() && cpfExistente.get().getId() != pessoa.getId()) {
            throw new CpfJaCadastrado("CPF já existente");
        }
    }

    public void verificarDependencias(Pessoa pessoa, List<Optional<Integer>> dependencias) {
        for (Optional<Integer> dependencia : dependencias) {
            if (dependencia.isPresent()) {
                throw new PessoaComDependencias("Não é possível excluir, pois o usuário possui dependências.");
            }
        }
    }
}























