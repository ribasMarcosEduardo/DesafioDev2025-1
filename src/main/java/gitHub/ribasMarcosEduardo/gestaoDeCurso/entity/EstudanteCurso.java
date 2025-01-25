package gitHub.ribasMarcosEduardo.gestaoDeCurso.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class EstudanteCurso {

    @Id
    @Column(name = "EST_CURSO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "CODPESSOA", nullable = false)
    private Pessoa estudante;

    @ManyToOne
    @JoinColumn(name = "CODCURSO", nullable = false)
    private Curso curso;


}
