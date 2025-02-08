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
        pessoa.setCpf(pessoa.getCpf().replaceAll("\\D", ""));
        validator.cpfExistEdit(pessoa);

        pessoa.setNome(pessoa.getNome());
        pessoa.setEmail(pessoa.getEmail());
        pessoa.setTelefone(pessoa.getTelefone());
        pessoa.setUsuario(pessoa.getUsuario());
        pessoa.setSenha(pessoa.getSenha());
        pessoa.setAtivo(pessoa.isAtivo());
        String encryptedPassword = new BCryptPasswordEncoder().encode(pessoa.getSenha());
        pessoa.setSenha(encryptedPassword);
        return repository.save(pessoa);
    }

    public void excluirPessoa(int id) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontrado("Pessoa não encontrado"));
        validator.verificarPendenciaPessoa(pessoa);
        repository.delete(pessoa);
    }




}
