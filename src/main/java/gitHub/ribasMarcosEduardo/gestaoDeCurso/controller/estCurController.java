package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.EstCursoDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Curso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.EstudanteCurso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.EstCursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/estudanteCurso")
public class estCurController {

    private final EstCursoService estCursoService;

    @PostMapping("matricularEstudante")
    public String matricularEstudante(@ModelAttribute EstCursoDTO estCursoDTO, RedirectAttributes redirectAttributes) {
        EstudanteCurso estudanteCurso = estCursoDTO.estCurMapear();
        estCursoService.matricularAluno(estudanteCurso);
        redirectAttributes.addFlashAttribute("matriculaSucess", "Matrícula finalizada!!");
        return "redirect:/matricula";
    }


}

