package gitHub.ribasMarcosEduardo.gestaoDeCurso.viewController;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ViewController {

    @GetMapping("menuPrincipal")
    public String menuPrincipal(){
        return "menuPrincipal";}// http://localhost:8080/menuPrincipal

    @GetMapping("cadastroPessoa")
    public String cadastroPessoa(Model model){
        model.addAttribute("pessoaDTO", new PessoaDTO(0,null,null,null,null,null,null,false));
        return "cadastroPessoa"; // http://localhost:8080/cadastroPessoa
    }

    @GetMapping("cadastroEndereco")
    public String cadastroEndereco(Model model){
        model.addAttribute("enderecoDTO", new EnderecoDTO(null,null,null,null,null));
        return "cadastroEndereco"; // http://localhost:8080/cadastroEndereco
    }

    @GetMapping("cadastroCurso")
    public String cadastroCurso(Model model){
        model.addAttribute("cursoDTO", new CursoDTO());
        return "cadastroCurso"; // http://localhost:8080/cadastroCurso
    }

    @GetMapping("matricula")
    public String matricula(Model model){
        model.addAttribute("estCursoDTO", new EstCursoDTO());
        return "matricula"; // http://localhost:8080/matricula
    }

    @GetMapping("profOferta")
    public String profOferta(Model model){
        model.addAttribute("profCurDTO", new ProfCurDTO());
        return "profOferta"; // http://localhost:8080/profOferta
    }

    @GetMapping("buscarPessoa")
    public String buscarPessoa() {
        return "buscarPessoa"; // http://localhost:8080/buscarPessoa
    }

    @GetMapping("editPessoa")
    public String editPessoa(@ModelAttribute("pessoaDTO") PessoaDTO pessoaDTO, Model model) {
        if (pessoaDTO.nome() == null) {
            return "redirect:/buscarPessoa";
        }
        model.addAttribute("pessoaDTO", pessoaDTO);
        return "editPessoa"; // http://localhost:8080/editPessoa
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }



}
