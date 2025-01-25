package gitHub.ribasMarcosEduardo.gestaoDeCurso.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Pessoa")
public class Pessoa{

    @Id
    @Column(name = "CODPESSOA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CPF", nullable = false , length = 11)
    private String cpf;

    @Column(name = "ATIVO", nullable = false)
    private boolean ativo;

    @Column(name = "USUARIO", nullable = false , length = 20)
    private String usuario;

    @Column(name = "SENHA", nullable = false)
    private String senha;

}