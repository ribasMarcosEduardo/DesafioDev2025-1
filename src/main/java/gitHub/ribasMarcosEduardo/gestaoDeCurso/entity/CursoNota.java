package gitHub.ribasMarcosEduardo.gestaoDeCurso.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CursoNota {

        @Id
        @Column(name = "EST_NOTA")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @ManyToOne
        @JoinColumn(name = "CODCURSO", nullable = false)
        private Curso curso;

        @ManyToOne
        @JoinColumn(name = "EST_CURSO", nullable = false)
        private EstudanteCurso estudante;

        @Enumerated(EnumType.STRING)
        @Column(name="tipNota")
        private TipNota tipNota;


        @Column(name = "valor")
        private Double valor;
}
