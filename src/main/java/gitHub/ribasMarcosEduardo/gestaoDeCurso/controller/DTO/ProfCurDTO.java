package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Curso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.EstudanteCurso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.ProfessorCurso;

public record ProfCurDTO(
        Pessoa CODPESSOA,
        Curso CODCURSO) {

    public ProfCurDTO(){
         this(null,null);
    }

    public ProfessorCurso mapearProfessor(){
        ProfessorCurso professorCurso = new ProfessorCurso();
        professorCurso.setProfessor(this.CODPESSOA);
        professorCurso.setCurso(this.CODCURSO);
        return professorCurso;
    }
}
