package gitHub.ribasMarcosEduardo.gestaoDeCurso.entity;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.PessoaDTO;
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

    @Column(name = "NOME", nullable = false,  length = 500)
    private String nome;

    @Column(name = "CPF", nullable = false , length = 11)
    private String cpf;

    @Column(name = "TELEFONE", nullable = false,  length = 20)
    private String telefone;

    @Column(name = "EMAIL", nullable = false,  length = 250)
    private String email;

    @Column(name = "ATIVO", nullable = false)
    private boolean ativo;

    @Column(name = "USUARIO", nullable = false , length = 20)
    private String usuario;

    @Column(name = "SENHA", nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

}