package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.EstCursoDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.EstudanteCurso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.EstCursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping("/estudanteCurso")
public class estCurController {

    private final EstCursoService estCursoService;

    // Matricula de Estudante:

    @PostMapping("matricularEstudante")
    public String matricularEstudante(@ModelAttribute EstCursoDTO estCursoDTO, RedirectAttributes redirectAttributes){
        EstudanteCurso estudanteCurso = estCursoDTO.estCurMapear();
        estCursoService.matricularAluno(estudanteCurso);
        redirectAttributes.addFlashAttribute("matriculaSucess", "Matrícula finalizada!!");
        return "redirect:/matricula";

    }

}
