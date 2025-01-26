package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.PessoaEndereco;

public record EnderecoDTO(
        Integer CODPESSOA,
        String cidade,
        String cep,
        String rua,
        Integer numero
) {

    public EnderecoDTO() {
        this(null,null,null,null, null); // Valores padrão
    }

    public PessoaEndereco mapearEndereco(Pessoa pessoa) {
        PessoaEndereco endereco = new PessoaEndereco();
        endereco.setPessoa(pessoa);
        endereco.setCidade(this.cidade);
        endereco.setCep(this.cep);
        endereco.setRua(this.rua);
        endereco.setNumero(this.numero);
        return endereco;
    }
}

