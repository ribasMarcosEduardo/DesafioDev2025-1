package gitHub.ribasMarcosEduardo.gestaoDeCurso.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PessoaEndereco {

    @Id
    @Column(name = "PES_ENDERECO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "CODPESSOA", nullable = false)
    private Pessoa pessoa;

    @Column(name = "CIDADE", length = 150)
    private String cidade;

    @Column(name = "CEP", length = 8)
    private String cep;

    @Column(name = "RUA", length = 150)
    private String rua;

    @Column(name = "NUM")
    private int numero;




}
