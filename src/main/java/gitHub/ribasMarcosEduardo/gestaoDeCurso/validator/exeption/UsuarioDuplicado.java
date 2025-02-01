package gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.exeption;

public class UsuarioDuplicado extends RuntimeException {
    public UsuarioDuplicado(String message) {
        super(message);
    }
}
