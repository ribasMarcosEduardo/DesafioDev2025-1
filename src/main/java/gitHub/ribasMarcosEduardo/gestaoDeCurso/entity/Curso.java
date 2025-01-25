package gitHub.ribasMarcosEduardo.gestaoDeCurso.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODCURSO")
    private Integer id;

    @Column(name = "NOM_CURSO" ,nullable = false , length = 150)
    private String nome;

    @Column(name = "ASSUNTO", nullable = false , length = 1000)
    private String assunto;

    @Column(name = "QNT_ENCONTRO", nullable = false)
    private int encontros;

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO", nullable = false)
    private Situacao situacao;



}
