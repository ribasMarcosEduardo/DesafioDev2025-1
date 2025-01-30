package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller;


import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.ProfCurDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.ProfessorCurso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping("/professor")
public class professorController {

    private final ProfessorService professorService;

    // Adicionar professor na oferta:

    @PostMapping("adicionarProfessor")
    public String adicionarProfessor(@ModelAttribute ProfCurDTO profCurDTO, RedirectAttributes redirectAttributes) {
        ProfessorCurso professor = profCurDTO.mapearProfessor();
        professorService.profCur(professor);
        redirectAttributes.addFlashAttribute("profAdicionado", "Professor adicionado");
        return "redirect:/profOferta";
    }


}
