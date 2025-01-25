package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.PessoaDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class pessoaController {

    private final PessoaService pessoaService;

    @PostMapping("salvarPessoa")
    public String salvarPessoa(@ModelAttribute PessoaDTO pessoaDTO) {
        System.out.println("PessoaDTO - Usuário: " + pessoaDTO.usuario()+", Ativo: " + pessoaDTO.ativo());
        Pessoa pessoa = pessoaDTO.mapearPessoa();
        pessoaService.salvarProduto(pessoa);
        return "redirect:http://localhost:8080/menuPrincipal";
    }


}
