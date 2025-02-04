package gitHub.ribasMarcosEduardo.gestaoDeCurso.service;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.EstCursoDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.EstudanteCurRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.PessoaRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.ProfessorRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.Validator;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.exeption.PessoaNaoEncontradaException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;
    private final Validator validator;
    private final ProfessorRepository professorRepository;
    private final EstudanteCurRepository estudanteCurRepository;

    public Pessoa salvarPessoa(Pessoa pessoa) {
        validator.usuarioDuplicado(pessoa);
        pessoa.setCpf(pessoa.getCpf().replaceAll("\\D", ""));
        return repository.save(pessoa);
    }

    public Pessoa buscarPessoaPorNome(String nome) {
        return repository.findByNome(nome)
                .orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa não encontrada"));
    }

    public Pessoa atualizarPessoa(Pessoa pessoa) {
        pessoa.setCpf(pessoa.getCpf().replaceAll("\\D", ""));
        validator.cpfExistEdit(pessoa);

        pessoa.setNome(pessoa.getNome());
        pessoa.setEmail(pessoa.getEmail());
        pessoa.setTelefone(pessoa.getTelefone());
        pessoa.setUsuario(pessoa.getUsuario());
        pessoa.setSenha(pessoa.getSenha());
        pessoa.setAtivo(pessoa.isAtivo());

        return repository.save(pessoa);
    }

    public void excluirPessoa(Pessoa pessoa) {
        List<Optional<Integer>> dependencias = Arrays.asList(
                professorRepository.findCodPessoaById(pessoa.getId()),
                estudanteCurRepository.findCodPessoaById(pessoa.getId())
        );

        validator.verificarDependencias(pessoa, dependencias);
        if (repository.existsById(pessoa.getId())){
            repository.deleteById(pessoa.getId());
        }
    }




}
