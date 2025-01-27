package gitHub.ribasMarcosEduardo.gestaoDeCurso.service;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Curso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository repository;

    public Curso salvarCurso(Curso curso) {
        return repository.save(curso);
    }
}
