package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Curso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.EstudanteCurso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;

public record EstCursoDTO (
        Pessoa CODPESSOA,
        Curso CODCURSO
){

    public EstCursoDTO(){
        this(null,null);
    }

    public EstudanteCurso estCurMapear(){
        EstudanteCurso estCurso = new EstudanteCurso();
        estCurso.setEstudante(this.CODPESSOA);
        estCurso.setCurso(this.CODCURSO);
        return estCurso;
    }
}
