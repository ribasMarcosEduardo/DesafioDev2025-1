package gitHub.ribasMarcosEduardo.gestaoDeCurso.service.exeption;
public class PessoaNaoEncontradaException extends RuntimeException {
    public PessoaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}