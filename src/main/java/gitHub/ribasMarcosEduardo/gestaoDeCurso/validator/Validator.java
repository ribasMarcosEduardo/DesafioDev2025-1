package gitHub.ribasMarcosEduardo.gestaoDeCurso.validator;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.*;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.*;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.exeption.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Validator {

    private final EnderecoRepository enderecoRepository;
    private final ProfessorRepository professorRepository;
    private final CursoRepository cursoRepository;
    private final PessoaRepository pessoaRepository;
    private final EstudanteCurRepository estudanteCurRepository;
    private final CursoPresencaRepository cursoPresencaRepository;

    // Validação de Endereço

    public void validarEndereco(PessoaEndereco endereco) {
        Optional<PessoaEndereco> enderecoExistente = enderecoRepository.findByPessoa_Id(endereco.getPessoa().getId());
        if (enderecoExistente.isPresent()) {
            throw new ObjetoDuplicado("A pessoa já possui um endereço cadastrado.");
        }
    }
    //------------------------------------------------------------------------------------------------------------------

    // Validação de curso

    public void validarCurso(Curso curso) {
        if (curso == null || curso.getId() == 0) {
            throw new ObjetoNaoEncontrado("O curso não foi encontrado.");
        }

        if (curso.getSituacao().equals(Situacao.Inativo)) {
            throw new ObjetoInativo("O curso inativo.");
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    // Validação de Pessoa

    public void validarPessoa(Pessoa pessoa){
        if (pessoa == null) {
            throw new ObjetoNaoEncontrado("Pessoa não encontrada.");
        }

        if (!pessoa.isAtivo()) {
            throw new ObjetoInativo("Pessoa inativo.");
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    // Validação de professor

    public void validarOferta(ProfessorCurso professor) {
        Optional<ProfessorCurso> ofertaExistente = professorRepository.findByCursoId(professor.getCurso().getId());
        if (ofertaExistente.isPresent()) {
            throw new ObjetoDuplicado("Essa oferta já tem um professor vinculado!");
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    // Validação de estudante

    public void validarMatricula(EstudanteCurso estudanteCurso) {
        Optional<EstudanteCurso> matriculado = estudanteCurRepository
                .findByEstudanteIdAndCursoId(estudanteCurso.getEstudante().getId(), estudanteCurso.getCurso().getId());

        if (matriculado.isPresent()) {
            throw new ObjetoDuplicado("O estudante já está matriculado neste curso!");
        }
    }



    //------------------------------------------------------------------------------------------------------------------

    // Valdação de curso duplicado

    public void cursoDuplicado(Curso curso) {
        Optional<Curso> cursoExistente = cursoRepository.findByNome(curso.getNome());
        if (cursoExistente.isPresent() && cursoExistente.get().getId() != curso.getId()) {
            throw new ObjetoDuplicado("Já existe um curso com o mesmo nome");
        }
    }
    // Validação de Cadastro duplicado

    public void usuarioDuplicado(Pessoa pessoa){
        Optional<Pessoa> usuarioExistente = pessoaRepository.findByUsuario(pessoa.getUsuario());
        if(usuarioExistente.isPresent()){
            throw new ObjetoDuplicado("Já escolheram esse nome!");
        }

        String cpfNormalizado = pessoa.getCpf().replaceAll("\\D", "");
        Optional<Pessoa> cpfExistente = pessoaRepository.findByCpf(cpfNormalizado);
        if (cpfExistente.isPresent()) {
            throw new ObjetoDuplicado("Usuário já cadastrado");
        }
    }





    //------------------------------------------------------------------------------------------------------------------

    // Validação tela de busca e edição

    public void cpfExistEdit(Pessoa pessoa) {
        String cpfNormalizado = pessoa.getCpf().replaceAll("\\D", "");
        Optional<Pessoa> cpfExistente = pessoaRepository.findByCpf(cpfNormalizado);
        if (cpfExistente.isPresent() && cpfExistente.get().getId() != pessoa.getId()) {
            throw new ObjetoDuplicado("CPF já existente");
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    // Pendências Pessoa

    public void verificarPendenciaPessoa(Pessoa pessoa) {
        boolean professor = professorRepository.existsByProfessor_Id(pessoa.getId());
        if (professor) {
            throw new Dependencias("Não é possível excluir, pois o usário é professor de uma oferta.");
        }

        boolean estudante = estudanteCurRepository.existsByEstudante_Id(pessoa.getId());
        if (estudante) {
            throw new Dependencias("Não é possível excluir, pois o usuário está matriculado em um curso.");
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    // Pendências curso

    public void verificarPendenciaCurso(Curso curso) {
        boolean professorVinculado = professorRepository.existsByCurso_Id(curso.getId());
        if (professorVinculado) {
            throw new Dependencias("Não é possível excluir, pois o curso possui professor na oferta.");
        }

        boolean estudanteVinculado = estudanteCurRepository.existsByCurso_Id(curso.getId());
        if (estudanteVinculado) {
            throw new Dependencias("Não é possível excluir, pois o curso possui estudantes matriculados.");
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    //Validar nota

    public void validarNota(CursoNota cursoNota){
        double nota = cursoNota.getValor();
        if(nota < 0 || nota > 10){
            throw new Dependencias("Nota Invalida!!");
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    // Validar Presença

    public void validarPresenca(int cursoId) {

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        long presencasRegistradas = cursoPresencaRepository.countPresencasByCursoId(cursoId);

        if (presencasRegistradas >= curso.getEncontros()) {
            throw new Dependencias("Não é possível registrar mais presenças do que os encontros permitidos.");
        }
    }






}























