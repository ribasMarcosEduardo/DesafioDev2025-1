package gitHub.ribasMarcosEduardo.gestaoDeCurso.viewController;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.*;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Curso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.CursoRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class ViewController {

    private final PessoaRepository pessoaRepository;
    private final CursoRepository cursoRepository;

    // Menu Principal do Sistema

    @GetMapping("menuPrincipal")
    public String menuPrincipal(){
        return "menuPrincipal";}// http://localhost:8080/menuPrincipal

    @GetMapping("menuPrincipalEstudante")
    public String menuPrincipalEstudante(){
        return "menuPrincipalEstudante";}// http://localhost:8080/menuPrincipalEstudante

    @GetMapping("menuPrincipalProfessor")
    public String menuPrincipalProfessor(){
        return "menuPrincipalProfessor";}// http://localhost:8080/menuPrincipalProfessor

    // Cadastro de Pessoa

    @GetMapping("cadastroPessoa")
    public String cadastroPessoa(Model model){
        model.addAttribute("pessoaDTO", new PessoaDTO(0,null,null ,null,null,null,null,true));
        return "cadastroPessoa"; // http://localhost:8080/cadastroPessoa
    }

    // Cadastro de Pessoa Endereço

    @GetMapping("cadastroEndereco")
    public String cadastroEndereco(Model model){
        model.addAttribute("enderecoDTO", new EnderecoDTO(null,null,null,null,null));
        return "cadastroEndereco"; // http://localhost:8080/cadastroEndereco
    }

    // Cadastro de Curso

    @GetMapping("cadastroCurso")
    public String cadastroCurso(Model model){
        model.addAttribute("cursoDTO", new CursoDTO(0,null,null,null,null));
        return "cadastroCurso"; // http://localhost:8080/cadastroCurso
    }

    // Matricula

    @GetMapping("matricula")
    public String matricula(Model model){
        model.addAttribute("estCursoDTO", new EstCursoDTO(null,null));
        return "matricula"; // http://localhost:8080/matricula
    }

    // Professor do Curso

    @GetMapping("profOferta")
    public String profOferta(Model model){
        model.addAttribute("profCurDTO", new ProfCurDTO());
        return "profOferta"; // http://localhost:8080/profOferta
    }

    // Buscar Pessoa

    @GetMapping("buscarPessoa")
    public String buscarPessoa() {
        return "buscarPessoa"; // http://localhost:8080/buscarPessoa
    }

    // Editar Pessoa

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

    // Buscar Curso

    @GetMapping("buscarCurso")
    public String buscarCurso() {
        return "buscarCurso"; // http://localhost:8080/buscarCurso
    }

    // Editar Curso

    @GetMapping("editCurso")
    public String editCurso(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id == null) {
            return "redirect:/buscarCurso";
        }
        Curso curso = cursoRepository.findById(id).orElse(null);

        CursoDTO cursoDTO = CursoDTO.capturarCurso(curso);
        model.addAttribute("cursoDTO", cursoDTO); http://localhost:8080/editCurso

        return "editCurso";
    }


    @GetMapping("login")
    public String login() {

        return "login";
    }


    @GetMapping("/cursoMatriculas")
    public String mostrarPaginaDeMatriculas() {

        return "cursoMatriculas"; // http://localhost:8080/cursoMatriculas
    }

    @GetMapping("/mostrarCursosEstudante")
    public String mostrarCursosEstudante() {

        return "mostrarCursosEstudante"; // http://localhost:8080/mostrarCursosEstudante
    }






}


