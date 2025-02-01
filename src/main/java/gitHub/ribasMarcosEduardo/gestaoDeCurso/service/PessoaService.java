package gitHub.ribasMarcosEduardo.gestaoDeCurso.service;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.PessoaRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.Validator;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.exeption.PessoaNaoEncontradaException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;
    private final Validator validator;

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
        Pessoa pessoaExistente = repository.findById(pessoa.getId()).get();
        pessoaExistente.setCpf(pessoa.getCpf().replaceAll("\\D", ""));
        validator.cpfExistEdit(pessoaExistente);

        pessoaExistente.setNome(pessoa.getNome());
        pessoaExistente.setEmail(pessoa.getEmail());
        pessoaExistente.setTelefone(pessoa.getTelefone());
        pessoaExistente.setUsuario(pessoa.getUsuario());
        pessoaExistente.setSenha(pessoa.getSenha());
        pessoaExistente.setAtivo(pessoa.isAtivo());

        return repository.save(pessoaExistente);
    }







}
