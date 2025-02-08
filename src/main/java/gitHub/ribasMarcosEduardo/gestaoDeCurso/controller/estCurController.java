package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.EstCursoDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Curso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.EstudanteCurso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.CursoRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.PessoaRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.EstCursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/estudanteCurso")
public class estCurController {

    private final EstCursoService estCursoService;
    private final PessoaRepository pessoaRepository;
    private final CursoRepository cursoRepository;

    @GetMapping("/pessoa/{id}")
    public ResponseEntity<?> buscarEstudante(@PathVariable int id) {
        return pessoaRepository.findById(id)
                .map(pessoa -> ResponseEntity.ok(Map.of("nome", pessoa.getNome())))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/curso/{id}")
    public ResponseEntity<?> buscarCurso(@PathVariable int id) {
        return cursoRepository.findById(id)
                .map(curso -> ResponseEntity.ok(Map.of("nome", curso.getNome())))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("matricularEstudante")
    public String matricularEstudante(@ModelAttribute EstCursoDTO estCursoDTO, RedirectAttributes redirectAttributes) {
        EstudanteCurso estudanteCurso = estCursoDTO.estCurMapear();
        estCursoService.matricularAluno(estudanteCurso);
        redirectAttributes.addFlashAttribute("matriculaSucess", "Matrícula finalizada!!");
        return "redirect:/matricula";
    }


}

