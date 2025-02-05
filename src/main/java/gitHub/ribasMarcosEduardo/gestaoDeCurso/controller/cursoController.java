package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.CursoDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Curso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.CursoRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping("/curso")
public class cursoController{

    private final CursoService cursoService;
    private final CursoRepository cursoRepository;

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
}






