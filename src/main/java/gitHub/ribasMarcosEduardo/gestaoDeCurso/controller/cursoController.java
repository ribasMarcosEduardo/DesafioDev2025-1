package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.CursoDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Curso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.EstudanteCurso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.CursoRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.EstudanteCurRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.ProfessorRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/curso")
public class cursoController {

    private final CursoService cursoService;
    private final EstudanteCurRepository estudanteCurRepository;
    private final ProfessorRepository professorRepository;

    //------------------------------------------------------------------------------------------------------------------

    // Cadastrar um novo curso - cadastroCurso

    @PostMapping("/salvarCurso")
    public String salvarCurso(@ModelAttribute CursoDTO cursoDTO, RedirectAttributes redirectAttributes) {
        Curso curso = cursoDTO.mapearCurso();
        cursoService.salvarCurso(curso);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Curso salva com sucesso!");
        return "redirect:/cadastroCurso";
    }

    //------------------------------------------------------------------------------------------------------------------

    // Buscar Curso pelo nome - barra de pesquisa para o edit

    @GetMapping("/buscarC")
    public String buscarC(@RequestParam String nome, Model model) {
        Curso curso = cursoService.buscarNomeCurso(nome);
        CursoDTO cursoDTO = CursoDTO.capturarCurso(curso);

        model.addAttribute(cursoDTO);
        return "editCurso";
    }

    //------------------------------------------------------------------------------------------------------------------

    // Atualizar curso - editCurso

    @PostMapping("/atualizarCurso")
    public String atualizarCurso(@ModelAttribute CursoDTO cursoDTO, RedirectAttributes redirectAttributes) {
        Curso curso = cursoDTO.mapearCurso();
        cursoService.atualizarCurso(curso);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Curso atualizada com sucesso!");
        return "redirect:/buscarCurso";
    }

    //------------------------------------------------------------------------------------------------------------------

    // Excluir curso - editCurso

    @DeleteMapping("/excluirCurso/{id}")
    public String excluirPessoa(@PathVariable int id, RedirectAttributes redirectAttributes) {
        cursoService.excluirCurso(id);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Curso excluído com sucesso!");
        return "redirect:/cadastroCurso";
    }

    //------------------------------------------------------------------------------------------------------------------

    // Relatório de estudantes por curso

    @PostMapping("/mostrarEstudantes")
    public String listarEstudantesPorCurso(@RequestParam String nomeCurso, Model model) {
        List<String> nomesEstudantes = estudanteCurRepository.findNomesEstudantesByCursoNome(nomeCurso);

        model.addAttribute("nomesEstudantes", nomesEstudantes);
        model.addAttribute("nomeCurso", nomeCurso);

        return "listaEstudantes";
    }

    //------------------------------------------------------------------------------------------------------------------

    // Relatório de curso por estudante

    @PostMapping("/mostrarCursosEstudante")
    public String listarCursosPorEstudante(@RequestParam String nomePessoa, Model model) {
        List<String> cursos = estudanteCurRepository.findCursosByPessoaNome(nomePessoa);

        model.addAttribute("cursos", cursos);
        model.addAttribute("nomePessoa", nomePessoa);

        return "listaCursosEstudante";
    }

    //------------------------------------------------------------------------------------------------------------------

    // Tabela de cursos do professor

    @GetMapping("/cursos")
    public String listarCursos(Model model, Principal principal) {

        String nomeProfessor = principal.getName();
        List<Curso> cursos = professorRepository.findCursosByProfessorNome(nomeProfessor);

        Map<Integer, Integer> quantidades = new HashMap<>();
        for (Curso curso : cursos) {
            int quantidade = estudanteCurRepository.countEstudantesByCursoId(curso.getId());
            quantidades.put(curso.getId(), quantidade);
        }

        model.addAttribute("cursos", cursos);
        model.addAttribute("quantidades", quantidades);

        return "menuPrincipalProfessor";
    }

    //------------------------------------------------------------------------------------------------------------------

    @GetMapping("/diarioDeClasse")
    public String diarioDeClasse(@RequestParam String nome, Model model) {
        List<EstudanteCurso> nomesEstudantes = estudanteCurRepository.findEstudantesComCodigoPorCursoNome(nome);

        model.addAttribute("nomesEstudantes", nomesEstudantes);
        model.addAttribute("nomeCurso", nome);
        return "diarioDeClasse";
    }


}












