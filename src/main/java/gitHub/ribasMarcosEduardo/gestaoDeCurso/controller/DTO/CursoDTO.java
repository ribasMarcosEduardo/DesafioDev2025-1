package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Curso;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Situacao;

public record CursoDTO(
        Integer id,
        String nome,
        String assunto,
        Integer encontros,
        String situacao
) {

    public Curso mapearCurso() {
        Curso curso = new Curso();
        curso.setId(this.id);
        curso.setNome(this.nome);
        curso.setAssunto(this.assunto);
        curso.setEncontros(this.encontros);
        curso.setSituacao(Situacao.valueOf(this.situacao));
        return curso;
    }

    public static CursoDTO capturarCurso(Curso curso) {
        return new CursoDTO(
                curso.getId(),
                curso.getNome(),
                curso.getAssunto(),
                curso.getEncontros(),
                curso.getSituacao().name()
                );

    }
}