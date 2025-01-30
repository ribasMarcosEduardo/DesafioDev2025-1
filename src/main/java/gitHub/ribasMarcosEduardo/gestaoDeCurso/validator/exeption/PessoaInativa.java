package gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.exeption;

public class PessoaInativa extends RuntimeException {
    public PessoaInativa(String message) {
        super(message);
    }
}
