package gitHub.ribasMarcosEduardo.gestaoDeCurso.service;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.EstudanteCurso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.EstudanteCurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstCursoService {

    private final EstudanteCurRepository estudanteCurRepository;

    public EstudanteCurso matricularAluno(EstudanteCurso estudanteCurso){
        return estudanteCurRepository.save(estudanteCurso);
    }
}
