package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.PessoaDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RequiredArgsConstructor
@Controller
@RequestMapping("/pessoa")
public class pessoaController {

    private final PessoaService pessoaService;

    @PostMapping("salvarPessoa")
    public String salvarPessoa(@ModelAttribute PessoaDTO pessoaDTO, RedirectAttributes redirectAttributes) {
        System.out.println("PessoaDTO - Usuário: " + pessoaDTO.usuario() + ", Ativo: " + pessoaDTO.ativo());
        Pessoa pessoa = pessoaDTO.mapearPessoa();
        pessoaService.salvarProduto(pessoa);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Pessoa salva com sucesso!");
        return "redirect:/cadastroPessoa";
    }


}
