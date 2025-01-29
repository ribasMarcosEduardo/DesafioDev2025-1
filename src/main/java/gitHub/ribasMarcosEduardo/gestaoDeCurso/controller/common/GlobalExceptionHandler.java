package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.common;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.exeption.CursoInativo;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.exeption.CursoNaoencontrado;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.exeption.EnderecoJaCadastradoException;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.exeption.PessoaNaoEncontradaException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CursoInativo.class)
    public String handleCursoInativo(CursoInativo e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("CursoInativo", e.getMessage());
        return "redirect:/matricula";
    }

    @ExceptionHandler(CursoNaoencontrado.class)
    public String handleCursoNaoEncontrado(CursoNaoencontrado e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("CursoNaoencontrado", e.getMessage());
        return "redirect:/matricula";
    }

    @ExceptionHandler(PessoaNaoEncontradaException.class)
    public String handlePessoaNaoEncontrada(PessoaNaoEncontradaException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("PessoaNaoEncontradaE", e.getMessage());
        return "redirect:/cadastroEndereco";
    }

    @ExceptionHandler(EnderecoJaCadastradoException.class)
    public String handleEnderecoJaCadastrado(EnderecoJaCadastradoException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mensagemErroJaCadastrado", e.getMessage());
        return "redirect:/cadastroEndereco";
    }
}