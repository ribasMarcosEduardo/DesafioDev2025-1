package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.CursoDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Curso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.EstudanteCurso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.CursoRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.EstudanteCurRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.CursoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CursoControllerTest {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private EstudanteCurRepository estudanteCurRepository;

    @Test
    @Transactional
    public void buscarCursoPorNomeTest() {
        String nomeCurso = "Java";

        Optional<Curso> cursoOptional = Optional.ofNullable(cursoService.buscarNomeCurso(nomeCurso));

        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();
            CursoDTO cursoDTO = CursoDTO.capturarCurso(curso);

            List<EstudanteCurso> estudantesCurso = estudanteCurRepository.findByCursoNome(curso.getNome());
            boolean existeEstudante = !estudantesCurso.isEmpty();

            System.out.println("Curso encontrado: " + cursoDTO.nome());
            System.out.println("Existe estudante relacionado? " + existeEstudante);
        } else {
            System.out.println("Nenhum curso encontrado com o nome: " + nomeCurso);
        }
    }

        @Test
        public void listarEstudantesPorCursoTest() {
            String nomeCurso = "Java";

            List<EstudanteCurso> estudantesCurso = estudanteCurRepository.findByCursoNome(nomeCurso);

            if (!estudantesCurso.isEmpty()) {
                estudantesCurso.forEach(estudanteCurso -> {
                    System.out.println("Nome do Estudante: " + estudanteCurso.getEstudante().getNome());
                });
            } else {
                System.out.println("Nenhum estudante encontrado para o curso: " + nomeCurso);
            }

        }

    @Test
    public void listarCursosTest() {
        var cursos = cursoRepository.findAll();
        cursos.forEach(curso -> System.out.println("Curso: " + curso));
    }

}
