package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;

public record PessoaDTO (
        String nome,
        String cpf,
        String email,
        String telefone,
        String usuario,
        String senha,
        boolean ativo
){

    public PessoaDTO() {

        this(null,null,null,null,null,null,false); // Valores padrão
    }

    public Pessoa mapearPessoa(){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(this.nome);
        pessoa.setCpf(this.cpf);
        pessoa.setEmail(this.email);
        pessoa.setTelefone(this.telefone);
        pessoa.setUsuario(this.usuario);
        pessoa.setSenha(this.senha);
        pessoa.setAtivo(this.ativo);
        return pessoa;
    }

}
