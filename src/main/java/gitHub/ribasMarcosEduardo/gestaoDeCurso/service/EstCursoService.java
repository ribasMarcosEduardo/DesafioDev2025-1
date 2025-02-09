package gitHub.ribasMarcosEduardo.gestaoDeCurso.service;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.*;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.CursoNotaRapository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.CursoRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.EstudanteCurRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.PessoaRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstCursoService {

    private final EstudanteCurRepository estudanteCurRepository;


    private final Validator validator;
    private final CursoNotaRapository cursoNotaRapository;
    private final CursoRepository cursoRepository;

    public EstudanteCurso matricularAluno(EstudanteCurso estudanteCurso){
        validator.validarPessoa(estudanteCurso.getEstudante());
        validator.validarCurso(estudanteCurso.getCurso());
        validator.validarMatricula(estudanteCurso);
        estudanteCurso.getEstudante().setRole(UserRole.ESTUDANTE);
        return estudanteCurRepository.save(estudanteCurso);
    }

    public CursoNota salvarNota(CursoNota nota){
        return cursoNotaRapository.save(nota);
    }


}
