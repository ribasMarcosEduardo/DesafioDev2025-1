package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.CursoDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Curso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.EstudanteCurso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.CursoRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.EstudanteCurRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.CursoService;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/curso")
public class cursoController {

    private final Validator validator;
    private final CursoService cursoService;
    private final CursoRepository cursoRepository;
    private final EstudanteCurRepository estudanteCurRepository;

    @PostMapping("/salvarCurso")
    public String salvarCurso(@ModelAttribute CursoDTO cursoDTO, RedirectAttributes redirectAttributes) {
        Curso curso = cursoDTO.mapearCurso();
        cursoService.salvarCurso(curso);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Curso salva com sucesso!");
        return "redirect:/cadastroCurso";
    }

    @GetMapping("/buscarC")
    public String buscarC(@RequestParam String nome, Model model) {
        Curso curso = cursoService.buscarNomeCurso(nome);
        CursoDTO cursoDTO = CursoDTO.capturarCurso(curso);

        model.addAttribute(cursoDTO);
        return "editCurso";
    }

    @PostMapping("/atualizarCurso")
    public String atualizarCurso(@ModelAttribute CursoDTO cursoDTO, RedirectAttributes redirectAttributes) {
        Curso curso = cursoDTO.mapearCurso();
        cursoService.atualizarCurso(curso);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Curso atualizada com sucesso!");
        return "redirect:/buscarCurso";
    }

    @DeleteMapping("/excluirCurso/{id}")
    public String excluirPessoa(@PathVariable int id, RedirectAttributes redirectAttributes) {
        cursoService.excluirCurso(id);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Curso excluído com sucesso!");
        return "redirect:/cadastroCurso";
    }

    @GetMapping("/cursoMatriculas")
    public String cursoMatriculas() {
        return "cursoMatriculas";
    }

    @PostMapping("/mostrarEstudantes")
    public String listarEstudantesPorCurso(@RequestParam String nomeCurso, Model model) {
        List<String> nomesEstudantes = estudanteCurRepository.findNomesEstudantesByCursoNome(nomeCurso);

        model.addAttribute("nomesEstudantes", nomesEstudantes);
        model.addAttribute("nomeCurso", nomeCurso);

        return "listaEstudantes";
    }

    @PostMapping("/mostrarCursosEstudante")
    public String listarCursosPorEstudante(@RequestParam String nomePessoa, Model model) {
        List<String> cursos = estudanteCurRepository.findCursosByPessoaNome(nomePessoa);

        model.addAttribute("cursos", cursos);
        model.addAttribute("nomePessoa", nomePessoa);

        return "listaCursosEstudante";
    }

}












