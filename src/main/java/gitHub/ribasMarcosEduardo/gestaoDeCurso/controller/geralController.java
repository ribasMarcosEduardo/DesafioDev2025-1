package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class geralController {

@GetMapping("/")
public String home(Model model) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();


    model.addAttribute("username", username);

    return "menuPrincipal";
}
}