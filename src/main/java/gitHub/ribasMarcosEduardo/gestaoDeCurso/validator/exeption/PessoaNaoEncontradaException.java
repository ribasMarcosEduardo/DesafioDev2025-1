package gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.exeption;
public class PessoaNaoEncontradaException extends RuntimeException {
    public PessoaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}