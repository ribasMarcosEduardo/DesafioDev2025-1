package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.EstCursoDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.NotasDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.PresencaDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.*;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.CursoNotaRapository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.CursoRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.EstudanteCurRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.PessoaRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.CursoService;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.EstCursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/estudanteCurso")
public class estCurController {

    private final EstCursoService estCursoService;
    private final PessoaRepository pessoaRepository;
    private final EstudanteCurRepository estudanteCurRepository;
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

    //------------------------------------------------------------------------------------------------------------------

    // Salvar nota

    @PostMapping("/lancarNota1")
    public String lancarNota1(@RequestParam("idEstudante") int idEstudante,
                              @RequestParam("nomeCurso") String nomeCurso,
                              @RequestParam("valor") double valor,
                              RedirectAttributes redirectAttributes) {
        return lancarNota(idEstudante, nomeCurso, valor, TipNota.NOTA1, redirectAttributes);
    }

    @PostMapping("/lancarNota2")
    public String lancarNota2(@RequestParam("idEstudante") int idEstudante,
                              @RequestParam("nomeCurso") String nomeCurso,
                              @RequestParam("valor") double valor,
                              RedirectAttributes redirectAttributes) {
        return lancarNota(idEstudante, nomeCurso, valor, TipNota.NOTA2, redirectAttributes);
    }

    @PostMapping("/lancarNota3")
    public String lancarNota3(@RequestParam("idEstudante") int idEstudante,
                              @RequestParam("nomeCurso") String nomeCurso,
                              @RequestParam("valor") double valor,
                              RedirectAttributes redirectAttributes) {
        return lancarNota(idEstudante, nomeCurso, valor, TipNota.NOTA3, redirectAttributes);
    }

    private String lancarNota(int idEstudante, String nomeCurso, double valor, TipNota tipoNota, RedirectAttributes redirectAttributes) {
        EstudanteCurso estudante = estudanteCurRepository.findById(idEstudante).get();
        Curso curso = cursoRepository.findById(cursoRepository.findCursoIdByNomeCurso(nomeCurso)).get();

        NotasDTO notasDTO = new NotasDTO(estudante, curso, tipoNota, valor);
        CursoNota nota = notasDTO.mapearNota();

        estCursoService.salvarNota(nota);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Nota lançada com sucesso!");

        return "redirect:http://localhost:8080/curso/diarioDeClasse?nome=" + nomeCurso;
    }

    //------------------------------------------------------------------------------------------------------------------

    // Lançar falta

    @PostMapping("/lancarFalta")
    public String lancarFalta(@RequestParam("idEstudante") int idEstudante,
                              @RequestParam("nomeCurso") String nomeCurso,
                              @RequestParam("presenca")Presenca presenca,
                              RedirectAttributes redirectAttributes) {

        EstudanteCurso estudante = estudanteCurRepository.findById(idEstudante).get();
        Curso curso = cursoRepository.findById(cursoRepository.findCursoIdByNomeCurso(nomeCurso)).get();

        PresencaDTO presencaDTO = new PresencaDTO(estudante,curso,presenca);
        CursoPresenca presenca1 = presencaDTO.mapearPresenca();

        estCursoService.salvarPresenca(presenca1);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Presenca lançada com sucesso!");

        return "redirect:http://localhost:8080/curso/diarioDeClasse?nome=" + nomeCurso;

    }


}









