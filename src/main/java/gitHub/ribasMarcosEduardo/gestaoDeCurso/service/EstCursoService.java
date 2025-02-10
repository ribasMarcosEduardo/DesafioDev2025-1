package gitHub.ribasMarcosEduardo.gestaoDeCurso.service;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.*;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.*;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstCursoService {

    private final EstudanteCurRepository estudanteCurRepository;
    private final CursoPresencaRepository cursoPresencaRepository;
    private final CursoNotaRapository cursoNotaRapository;
    private final Validator validator;



    public EstudanteCurso matricularAluno(EstudanteCurso estudanteCurso){
        validator.validarPessoa(estudanteCurso.getEstudante());
        validator.validarCurso(estudanteCurso.getCurso());
        validator.validarMatricula(estudanteCurso);
        estudanteCurso.getEstudante().setRole(UserRole.ESTUDANTE);
        return estudanteCurRepository.save(estudanteCurso);
    }

    public CursoNota salvarNota(CursoNota nota){
        validator.validarNota(nota);
        return cursoNotaRapository.save(nota);
    }

    public CursoPresenca salvarPresenca(CursoPresenca presenca){
        validator.validarPresenca(presenca.getCurso().getId());
        return cursoPresencaRepository.save(presenca);
    }


}
