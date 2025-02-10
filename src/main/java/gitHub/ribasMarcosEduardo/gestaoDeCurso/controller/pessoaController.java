package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.PessoaDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.PessoaRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.PessoaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Optional;


@RequiredArgsConstructor
@Controller
@RequestMapping("/pessoa")
public class pessoaController {

    private final PessoaService pessoaService;
    private final PessoaRepository pessoaRepository;

    @PostMapping("salvarPessoa")
    public String salvarPessoa(@ModelAttribute PessoaDTO pessoaDTO, RedirectAttributes redirectAttributes) {
        Pessoa pessoa = pessoaDTO.mapearPessoa();
        pessoaService.salvarPessoa(pessoa);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Pessoa salva com sucesso!");
        return "redirect:/cadastroPessoa";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam String nome, Model model) {
        Pessoa pessoa = pessoaService.buscarPessoaPorNome(nome);
        PessoaDTO pessoaDTO = PessoaDTO.capturarPessoa(pessoa);

        model.addAttribute("pessoaDTO", pessoaDTO);
        return "editPessoa";
    }
    @PostMapping("/atualizarPessoa")
    public String atualizarPessoa(@ModelAttribute PessoaDTO pessoaDTO, RedirectAttributes redirectAttributes) {
        Pessoa pessoa = pessoaDTO.mapearPessoa();
        pessoaService.atualizarPessoa(pessoa);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Pessoa atualizada com sucesso!");
        return "redirect:/buscarPessoa";
    }


    @DeleteMapping("/excluirPessoa/{id}")
    public String excluirPessoa(@PathVariable int id, RedirectAttributes redirectAttributes) {
        pessoaService.excluirPessoa(id);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Pessoa excluída com sucesso!");
        return "redirect:/cadastroPessoa";
    }

    @GetMapping("/editar")
    public String exibirEdicaoPessoa(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        String usuario = userDetails.getUsername();
        Optional<Pessoa> pessoa = pessoaRepository.findByUsuario(usuario);

        if (pessoa == null) {
            return "redirect:/erro";
        }

        PessoaDTO pessoaDTO = PessoaDTO.capturarPessoa(pessoa.get());
        model.addAttribute("pessoaDTO", pessoaDTO);
        return "editUsuario";
    }

    @PostMapping("/atualizarUsuario")
    public String atualizarUsuario(@ModelAttribute PessoaDTO pessoaDTO, RedirectAttributes redirectAttributes, Principal principal) {
        Pessoa pessoa = pessoaDTO.mapearPessoa();
        pessoaService.atualizarPessoa(pessoa);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Pessoa atualizada com sucesso!");

        String usuarioLogado = principal.getName();

        Optional<Pessoa> pessoaLogadaOpt = pessoaRepository.findByUsuario(usuarioLogado);

        if (pessoaLogadaOpt.isPresent() && pessoaLogadaOpt.get().getRole() != null) {
            Pessoa pessoaLogada = pessoaLogadaOpt.get();
            switch (pessoaLogada.getRole()) {
                case ADMIN:
                    return "redirect:/menuPrincipal";
                case PROFESSOR:
                    return "redirect:/menuPrincipalProfessor";
                case ESTUDANTE:
                    return "redirect:/menuPrincipalEstudante";
                default:
                    return "redirect:/menuPrincipal";
            }
        } else {

            return "redirect:/menuPrincipal";
        }
    }









}
