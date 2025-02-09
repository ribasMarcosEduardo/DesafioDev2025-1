package gitHub.ribasMarcosEduardo.gestaoDeCurso.service;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Curso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.CursoNotaRapository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.CursoRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.EstudanteCurRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.Validator;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.exeption.ObjetoNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository repository;
    private final CursoNotaRapository cursoNotaRepository;
    private final EstudanteCurRepository estudanteCurRepository;
    private final Validator validator;

    public Curso salvarCurso(Curso curso) {
        validator.cursoDuplicado(curso);
        return repository.save(curso);
    }

    public Curso buscarNomeCurso(String nome) {
        return repository.findByNome(nome)
                .orElseThrow(() -> new ObjetoNaoEncontrado("Curso não encontrada"));
    }

    public Curso atualizarCurso(Curso curso) {

        validator.cursoDuplicado(curso);
        curso.setNome(curso.getNome());
        curso.setEncontros(curso.getEncontros());
        curso.setSituacao(curso.getSituacao());
        curso.setAssunto(curso.getAssunto());

        return repository.save(curso);
    }

    public void excluirCurso(int id) {
        Curso curso = repository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontrado("Curso não encontrado"));
        validator.verificarPendenciaCurso(curso);
        repository.delete(curso);
    }


}


