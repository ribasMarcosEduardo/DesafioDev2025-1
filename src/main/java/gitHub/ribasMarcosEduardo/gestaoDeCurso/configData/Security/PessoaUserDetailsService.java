package gitHub.ribasMarcosEduardo.gestaoDeCurso.configData.Security;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.PessoaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PessoaUserDetailsService implements UserDetailsService {

    private final PessoaRepository pessoaRepository;

    public PessoaUserDetailsService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Pessoa pessoa = pessoaRepository.findByUsuario(usuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return User.withUsername(pessoa.getUsuario())
                .password(pessoa.getSenha()) // Senha já criptografada no banco
                .roles("USER")
                .build();
    }
}

