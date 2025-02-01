package gitHub.ribasMarcosEduardo.gestaoDeCurso.configData.Security;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class PessoaUserDetails implements UserDetails {

    private final Pessoa pessoa;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retorna uma permissão fixa "USER". Se precisar de permissões dinâmicas
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return pessoa.getSenha();
    }

    @Override
    public String getUsername() {
        return pessoa.getUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Conta nunca expira
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Conta nunca é bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Credenciais nunca expiram
    }

    @Override
    public boolean isEnabled() {
        return pessoa.isAtivo(); // Status de ativação do usuário
    }

    public Pessoa getPessoa() {
        return this.pessoa;
    }

    @Override
    public String toString() {
        return "PessoaUserDetails{" +
                "usuario=" + pessoa.getUsuario() +
                ", ativo=" + pessoa.isAtivo() +
                '}';
    }

}