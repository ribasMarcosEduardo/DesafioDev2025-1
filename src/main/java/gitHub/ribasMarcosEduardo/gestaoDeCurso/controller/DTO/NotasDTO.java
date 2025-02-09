package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.*;

public record NotasDTO(
        EstudanteCurso estudante,
        Curso curso,
        TipNota tipNota,
        Double valor
) {

    public NotasDTO() {
        this(null,null,null,0.0);
    }

    public CursoNota mapearNota(){
        CursoNota nota = new CursoNota();
        nota.setEstudante(this.estudante());
        nota.setCurso(this.curso());
        nota.setValor(this.valor());
        nota.setTipNota(TipNota.valueOf(this.tipNota.name()));
        return nota;
    }

}
