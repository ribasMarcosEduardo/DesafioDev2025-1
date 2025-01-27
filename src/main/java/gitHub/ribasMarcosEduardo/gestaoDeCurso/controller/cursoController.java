package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.CursoDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Curso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping("/curso")
public class cursoController{

    private final CursoService cursoService;

    @PostMapping("salvarCurso")
    public String salvarCurso(@ModelAttribute CursoDTO cursoDTO, RedirectAttributes redirectAttributes) {
        Curso curso = CursoDTO.ma
        cursoService.salvarCurso(curso);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Pessoa salva com sucesso!");
        return "redirect:/cadastroPessoa";
    }
}
