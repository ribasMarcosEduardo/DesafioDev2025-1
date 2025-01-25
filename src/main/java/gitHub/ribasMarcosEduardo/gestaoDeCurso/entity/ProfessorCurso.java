package gitHub.ribasMarcosEduardo.gestaoDeCurso.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProfessorCurso {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "CODPESSOA", nullable = false)
    private Pessoa professor;

    @OneToOne
    @JoinColumn(name = "CODCURSO", nullable = false, unique = true)
    private Curso curso;

}
