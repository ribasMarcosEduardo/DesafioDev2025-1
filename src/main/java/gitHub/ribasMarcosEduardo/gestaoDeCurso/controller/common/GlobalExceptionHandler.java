package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.common;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.exeption.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CursoInativo.class)
    public String handleCursoInativo(CursoInativo e, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("CursoInativo", e.getMessage());
        return getRedirectUrl(request); // retorna para a origem
    }

    @ExceptionHandler(CursoNaoencontrado.class)
    public String handleCursoNaoEncontrado(CursoNaoencontrado e, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("CursoNaoencontrado", e.getMessage());
        return getRedirectUrl(request);
    }

    @ExceptionHandler(PessoaNaoEncontradaException.class)
    public String handlePessoaNaoEncontrada(PessoaNaoEncontradaException e, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("PessoaNaoEncontradaE", e.getMessage());
        return getRedirectUrl(request);
    }

    @ExceptionHandler(PessoaInativa.class)
    public String handlePessoaInativa(PessoaInativa e,RedirectAttributes redirectAttributes, HttpServletRequest request){
        redirectAttributes.addFlashAttribute("PessoaInativa", e.getMessage());
        return getRedirectUrl(request);
    }

    @ExceptionHandler(CpfJaCadastrado.class)
    public String andleCpfJaCadastrado(CpfJaCadastrado e,RedirectAttributes redirectAttributes, HttpServletRequest request){
        redirectAttributes.addFlashAttribute("cpfJaCadastrado", e.getMessage());
        return getRedirectUrl(request);
    }

    private String getRedirectUrl(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/matricula");
    }

    @ExceptionHandler(EnderecoJaCadastradoException.class)
    public String handleEnderecoJaCadastrado(EnderecoJaCadastradoException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mensagemErroJaCadastrado", e.getMessage());
        return "redirect:/cadastroEndereco";
    }

    @ExceptionHandler(ProfessorDuplicado.class)
    public String handleProfessorDuplicado(ProfessorDuplicado e, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("ProfessorDuplicado", e.getMessage());
        return "redirect:/profOferta";
    }

    @ExceptionHandler(CursoDuplicado.class)
    public String handleCursoDuplicado(CursoDuplicado e, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("cursoDuplicado",e.getMessage());
        return "redirect:/cadastroCurso";
    }

    @ExceptionHandler(UsuarioDuplicado.class)
    public String handleUsuarioDuplicado(UsuarioDuplicado e, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("usuarioDuplicado",e.getMessage());
        return "redirect:/cadastroPessoa";
    }

    @ExceptionHandler(PessoaComDependencias.class)
    public String handlePessoaComDependencias(PessoaComDependencias e, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("pessoaComDependencias",e.getMessage());
        return "redirect:/buscarPessoa";
    }


    @ModelAttribute("username")
    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return null;
    }


}