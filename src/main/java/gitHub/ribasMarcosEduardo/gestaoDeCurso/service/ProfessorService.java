package gitHub.ribasMarcosEduardo.gestaoDeCurso.service;


import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.ProfessorCurso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.ProfessorRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final Validator validator;

    public ProfessorCurso profCur(ProfessorCurso professorCurso){
        validator.validarPessoa(professorCurso.getProfessor());
        validator.validarCurso(professorCurso.getCurso());
        validator.validarOferta(professorCurso);
        return professorRepository.save(professorCurso);
    }


}
