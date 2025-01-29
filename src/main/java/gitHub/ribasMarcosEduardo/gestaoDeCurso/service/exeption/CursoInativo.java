package gitHub.ribasMarcosEduardo.gestaoDeCurso.service.exeption;

public class CursoInativo extends RuntimeException {
    public CursoInativo(String message) {
        super(message);
    }
}
