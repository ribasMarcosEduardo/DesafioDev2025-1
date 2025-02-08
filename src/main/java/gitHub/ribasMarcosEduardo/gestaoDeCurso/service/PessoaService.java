package gitHub.ribasMarcosEduardo.gestaoDeCurso.service;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.EstudanteCurRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.PessoaRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.ProfessorRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.Validator;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.exeption.ObjetoNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
        String encryptedPassword = new BCryptPasswordEncoder().encode(pessoa.getSenha());
        pessoa.setSenha(encryptedPassword);
        return repository.save(pessoa);
    }

    public Pessoa buscarPessoaPorNome(String nome) {
        return repository.findByNome(nome)
                .orElseThrow(() -> new ObjetoNaoEncontrado("Pessoa não encontrada"));
    }

    public Pessoa atualizarPessoa(Pessoa pessoa) {
        Pessoa pessoaExistente = repository.findById(pessoa.getId())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        pessoaExistente.setNome(pessoa.getNome());
        pessoaExistente.setEmail(pessoa.getEmail());
        pessoaExistente.setTelefone(pessoa.getTelefone());
        pessoaExistente.setUsuario(pessoa.getUsuario());
        pessoaExistente.setAtivo(pessoa.isAtivo());

        if (pessoa.getSenha() != null && !pessoa.getSenha().isEmpty()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            pessoaExistente.setSenha(passwordEncoder.encode(pessoa.getSenha()));
        }

        validator.cpfExistEdit(pessoaExistente);
        return repository.save(pessoaExistente);
    }

    public void excluirPessoa(int id) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontrado("Pessoa não encontrado"));
        validator.verificarPendenciaPessoa(pessoa);
        repository.delete(pessoa);
    }




}
