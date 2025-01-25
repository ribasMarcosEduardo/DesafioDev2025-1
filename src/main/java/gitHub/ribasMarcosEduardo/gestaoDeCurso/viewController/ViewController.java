package gitHub.ribasMarcosEduardo.gestaoDeCurso.viewController;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.PessoaDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("menuPrincipal")
    public String menuPrincipal(){
        return "menuPrincipal";
    }// http://localhost:8080/menuPrincipal

    @GetMapping("cadastroPessoa")
    public String cadastroPessoa(Model model){
        model.addAttribute("pessoaDTO", new PessoaDTO());
        return "cadastroPessoa"; // http://localhost:8080/cadastroPessoa
    }


}
