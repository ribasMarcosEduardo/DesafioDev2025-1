package gitHub.ribasMarcosEduardo.gestaoDeCurso.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CursoPresenca {

    @Id
    @Column(name = "CUR_PRESENCA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "EST_CURSO", nullable = false)
    private EstudanteCurso estudante;

    @ManyToOne
    @JoinColumn(name = "CODCURSO", nullable = false)
    private Curso curso;


}
