package gitHub.ribasMarcosEduardo.gestaoDeCurso.viewController;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.*;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.PessoaRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.EstCursoService;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.PessoaService;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.exeption.PessoaNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ViewController {

    private final PessoaRepository pessoaRepository;

    @GetMapping("menuPrincipal")
    public String menuPrincipal(){
        return "menuPrincipal";}// http://localhost:8080/menuPrincipal

    @GetMapping("cadastroPessoa")
    public String cadastroPessoa(Model model){
        model.addAttribute("pessoaDTO", new PessoaDTO(0,null,null,null,null,null,null,true));
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
        model.addAttribute("estCursoDTO", new EstCursoDTO(null,null));
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
    public String editPessoa(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id == null) {
            return "redirect:/buscarPessoa";
        }
        Pessoa pessoa = pessoaRepository.findById(id).orElse(null);
        PessoaDTO pessoaDTO = PessoaDTO.capturarPessoa(pessoa);
        model.addAttribute("pessoaDTO", pessoaDTO);

        return "editPessoa";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }


}


