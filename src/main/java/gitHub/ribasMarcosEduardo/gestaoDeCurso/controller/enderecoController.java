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
public class enderecoController {

    private final EnderecoService enderecoService;

    @PostMapping("salvarEndereco")
    public String salvarEndereco(@ModelAttribute EnderecoDTO enderecoDTO, RedirectAttributes redirectAttributes) {
        try {
            System.out.println("EnderecoDTO - Pessoa: " + enderecoDTO.CODPESSOA()); // Debug

            Pessoa pessoa = enderecoService.buscarPessoa(enderecoDTO.CODPESSOA()); // Busca a pessoa
            PessoaEndereco endereco = enderecoDTO.mapearEndereco(pessoa);          // Mapeia o DTO para a entidade

            enderecoService.salvarEndereco(endereco);                              // Salva o endereço

            redirectAttributes.addFlashAttribute("mensagemSucesso", "Endereço salvo com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao salvar endereço: " + e.getMessage());
            e.printStackTrace(); // Debug de erros
        }

        return "redirect:/cadastroEndereco";
    }

}
