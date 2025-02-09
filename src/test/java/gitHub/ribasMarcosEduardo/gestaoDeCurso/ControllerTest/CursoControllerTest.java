package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.CursoDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Curso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.CursoNota;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.EstudanteCurso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.TipNota;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.CursoRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.EstudanteCurRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.ProfessorRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.CursoService;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.EstCursoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CursoControllerTest {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private EstCursoService estCursoService;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private EstudanteCurRepository estudanteCurRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    /*@Test
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
    }*/

    @Test
    public void listarCursosPorProfessorTest() {
        String nomeProfessor = "" + "professor2";

        List<Curso> cursos = professorRepository.findCursosByProfessorNome(nomeProfessor);

        if (!cursos.isEmpty()) {
            cursos.forEach(curso -> {
                System.out.println("Curso Ministrado: " + curso.getNome());
            });
        } else {
            System.out.println("Nenhum curso encontrado para o professor: " + nomeProfessor);
        }
    }


    @Test
    public void testLancarNota() {
        // Dados de teste: IDs já existentes de estudante e curso
        int idEstudante = 3;  // ID do estudante já existente
        int idCurso = 1;       // ID do curso já existente
        double valorNota = 8.5;

        // Buscando estudante e curso no banco
        EstudanteCurso estudante = estudanteCurRepository.findById(idEstudante).orElseThrow();
        Curso curso = cursoRepository.findById(idCurso).orElseThrow();

        // Criando e configurando a nota
        CursoNota nota = new CursoNota();
        nota.setEstudante(estudante);
        nota.setCurso(curso);
        nota.setTipNota(TipNota.NOTA1);
        nota.setValor(valorNota);

        // Salvando no repositório
        estCursoService.salvarNota(nota);

        // Verificando se a nota foi salva corretamente
        assertThat(nota.getId()).isGreaterThan(0);  // Verifica se a nota tem ID após o salvamento
        assertThat(nota.getEstudante().getId()).isEqualTo(idEstudante);  // Verifica se o estudante é o correto
        assertThat(nota.getCurso().getId()).isEqualTo(idCurso);  // Verifica se o curso é o correto
        assertThat(nota.getValor()).isEqualTo(valorNota);  // Verifica se a nota foi salva corretamente
    }
}








