package gitHub.ribasMarcosEduardo.gestaoDeCurso.configData.Security;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.PessoaRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final PessoaRepository pessoaRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String username = ((User) authentication.getPrincipal()).getUsername();
        Pessoa pessoa = pessoaRepository.findByUsuario(username).orElse(null);

        if (pessoa != null) {
            switch (pessoa.getRole()) {
                case ADMIN:
                    setDefaultTargetUrl("/menuPrincipal");
                    break;
                case PROFESSOR:
                    setDefaultTargetUrl("/menuPrincipal");
                    break;
                case ESTUDANTE:
                    setDefaultTargetUrl("/menuPrincipalEstudante");
                    break;
                default:
                    setDefaultTargetUrl("/menuPrincipal");
                    break;
            }
        } else {
            setDefaultTargetUrl("/menuPrincipal");
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
