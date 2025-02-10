package gitHub.ribasMarcosEduardo.gestaoDeCurso.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PessoaEndereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PES_ENDERECO")
    private int id;

    @OneToOne
    @JoinColumn(name = "CODPESSOA", nullable = false)
    private Pessoa pessoa;

    @Column(name = "CIDADE", length = 150, nullable = false)
    private String cidade;

    @Column(name = "CEP",nullable = false)
    private String cep;

    @Column(name = "RUA", length = 150, nullable = false)
    private String rua;

    @Column(name = "NUM", nullable = false)
    private int numero;
}
