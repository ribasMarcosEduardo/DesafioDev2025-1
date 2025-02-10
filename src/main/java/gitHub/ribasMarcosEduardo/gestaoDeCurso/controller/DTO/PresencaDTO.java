package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.*;

public record PresencaDTO(
        EstudanteCurso estudante,
        Curso curso,
        Presenca presenca
) {

    public CursoPresenca mapearPresenca(){
        CursoPresenca presenca1 = new CursoPresenca();
        presenca1.setEstudante(this.estudante());
        presenca1.setCurso(this.curso());
        presenca1.setPresenca(this.presenca());
        return presenca1;
    }


}
