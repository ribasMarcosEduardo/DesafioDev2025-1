package gitHub.ribasMarcosEduardo.gestaoDeCurso.configData.Security;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.PessoaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, PessoaUserDetailsService pessoaUserDetailsService,
                                                   PessoaRepository pessoaRepository) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers( "/menuPrincipal", "/cadastroPessoa").hasAnyRole("ADMIN", "PROFESSOR")
                .requestMatchers( "/menuPrincipalEstudante").hasRole("ESTUDANTE")


                .anyRequest().authenticated()
                )
                .formLogin(configurer -> configurer
                        .loginPage("/login")
                        .successHandler(customAuthSuccessHandler(pessoaRepository))
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                )
                .userDetailsService(pessoaUserDetailsService)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PessoaUserDetailsService pessoaUserDetailsService(PessoaRepository pessoaRepository) {
        return new PessoaUserDetailsService(pessoaRepository);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/css/**", "/js/**", "/images/**", "/style.css");
    }

    @Bean
    public CustomAuthSuccessHandler customAuthSuccessHandler(PessoaRepository pessoaRepository) {
        return new CustomAuthSuccessHandler(pessoaRepository);
    }
}