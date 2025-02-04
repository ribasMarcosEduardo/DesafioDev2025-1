package gitHub.ribasMarcosEduardo.gestaoDeCurso.controller;

import gitHub.ribasMarcosEduardo.gestaoDeCurso.controller.DTO.PessoaDTO;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.entity.Pessoa;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.repository.PessoaRepository;
import gitHub.ribasMarcosEduardo.gestaoDeCurso.service.PessoaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RequiredArgsConstructor
@Controller
@RequestMapping("/pessoa")
public class pessoaController {

    private final PessoaService pessoaService;
    private final PessoaRepository pessoaRepository;

    @PostMapping("salvarPessoa")
    public String salvarPessoa(@ModelAttribute PessoaDTO pessoaDTO, RedirectAttributes redirectAttributes) {
        System.out.println("PessoaDTO - Usuário: " + pessoaDTO.usuario() + ", Ativo: " + pessoaDTO.ativo());
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
    public String excluirPessoa(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada."));
        pessoaService.excluirPessoa(pessoa);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Pessoa excluída com sucesso!");
        return "redirect:/buscarPessoa";
    }







}
