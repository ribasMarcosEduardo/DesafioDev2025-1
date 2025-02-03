package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller;


import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.EnderecoDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.PessoaEndereco;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping("/endereco")
public class enderecoController{

    private final EnderecoService enderecoService;

    @PostMapping("salvarEndereco")
    public String salvarEndereco(@ModelAttribute EnderecoDTO enderecoDTO, RedirectAttributes redirectAttributes) {
        PessoaEndereco endereco = enderecoDTO.mapearEndereco();
        enderecoService.salvarEndereco(endereco);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Endereço salvo com sucesso!");
        return "redirect:/cadastroEndereco";
    }

}
