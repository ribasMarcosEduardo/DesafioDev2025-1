package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.PessoaEndereco;

public record EnderecoDTO(
        Pessoa CODPESSOA,
        String cidade,
        String cep,
        String rua,
        Integer numero
) {

    public PessoaEndereco mapearEndereco() {
        PessoaEndereco endereco = new PessoaEndereco();
        endereco.setPessoa(this.CODPESSOA);
        endereco.setCidade(this.cidade);
        endereco.setCep(this.cep);
        endereco.setRua(this.rua);
        endereco.setNumero(this.numero);
        return endereco;
    }
}

