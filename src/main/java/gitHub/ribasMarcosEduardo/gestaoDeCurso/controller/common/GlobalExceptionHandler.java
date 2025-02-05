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

    @ExceptionHandler(ObjetoNaoEncontrado.class)
    public String handleObjetoNaoEncontrado(ObjetoNaoEncontrado e, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("ObjetoNaoEncontrado", e.getMessage());
        return getRedirectUrl(request);
    }

    @ExceptionHandler(ObjetoInativo.class)
    public String handlePessoaInativa(ObjetoInativo e, RedirectAttributes redirectAttributes, HttpServletRequest request){
        redirectAttributes.addFlashAttribute("ObjetoInativo", e.getMessage());
        return getRedirectUrl(request);
    }

    @ExceptionHandler(Dependencias.class)
    public String handleDependencias(Dependencias e, RedirectAttributes redirectAttributes, HttpServletRequest request){
        redirectAttributes.addFlashAttribute("Dependencias",e.getMessage());
        return getRedirectUrl(request);
    }


    @ExceptionHandler(ObjetoDuplicado.class)
    public String handleObjetoDuplicado(ObjetoDuplicado e, RedirectAttributes redirectAttributes, HttpServletRequest request){
        redirectAttributes.addFlashAttribute("ObjetoDuplicado",e.getMessage());
        return getRedirectUrl(request);
    }

    private String getRedirectUrl(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/matricula");
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