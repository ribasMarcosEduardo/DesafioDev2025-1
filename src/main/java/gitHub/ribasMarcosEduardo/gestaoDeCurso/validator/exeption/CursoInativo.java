package gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.exeption;

public class CursoInativo extends RuntimeException {
    public CursoInativo(String message) {
        super(message);
    }
}
