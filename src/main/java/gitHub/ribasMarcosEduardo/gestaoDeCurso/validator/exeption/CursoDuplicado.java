package gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.exeption;

public class CursoDuplicado extends RuntimeException {
    public CursoDuplicado(String message) {
        super(message);
    }
}
