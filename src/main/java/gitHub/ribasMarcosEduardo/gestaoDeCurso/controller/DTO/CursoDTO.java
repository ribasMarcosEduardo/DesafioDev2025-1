package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Curso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Situacao;

public record CursoDTO(
        String nome,
        String assunto,
        Integer encontros,
        String situacao
) {

    public CursoDTO(){
        this(null,null,null,null);
    }

    public Curso mapearCurso() {
        Curso curso = new Curso();
        curso.setNome(this.nome);
        curso.setAssunto(this.assunto);
        curso.setEncontros(this.encontros);
        curso.setSituacao(Situacao.valueOf(this.situacao));
        return curso;
    }

}
