package gitHub.ribasMarcosEduardo.gestaoDeCurso.service;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.EstCursoDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.EstudanteCurso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.EstudanteCurRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstCursoService {

    private final EstudanteCurRepository estudanteCurRepository;
    private final Validator validator;

    public EstudanteCurso matricularAluno(EstudanteCurso estudanteCurso){
        validator.validarPessoa(estudanteCurso.getEstudante());
        validator.validarCurso(estudanteCurso.getCurso());
        return estudanteCurRepository.save(estudanteCurso);
    }


}
