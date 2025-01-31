package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;

public record PessoaDTO (
        int id, // Adicionando o ID
        String nome,
        String cpf,
        String email,
        String telefone,
        String usuario,
        String senha,
        boolean ativo
){
    public Pessoa mapearPessoa(){
        Pessoa pessoa = new Pessoa();
        pessoa.setId(this.id); // Agora o ID será mapeado corretamente
        pessoa.setNome(this.nome);
        pessoa.setCpf(this.cpf);
        pessoa.setEmail(this.email);
        pessoa.setTelefone(this.telefone);
        pessoa.setUsuario(this.usuario);
        pessoa.setSenha(this.senha);
        pessoa.setAtivo(this.ativo);
        return pessoa;
    }

    public static PessoaDTO capturarPessoa(Pessoa pessoa) {
        return new PessoaDTO(
                pessoa.getId(),  // Capturando o ID
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getEmail(),
                pessoa.getTelefone(),
                pessoa.getUsuario(),
                pessoa.getSenha(),
                pessoa.isAtivo()
        );
    }
}
