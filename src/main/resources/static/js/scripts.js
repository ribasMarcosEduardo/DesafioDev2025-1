


// minúsculo


    document.getElementById('usuario').addEventListener('input', function() {
        this.value = this.value.toLowerCase();
    });



document.addEventListener("DOMContentLoaded", function () {
    const cpfInput = document.getElementById("cpfPosiciona");

    if (!cpfInput) return; // Garante que o campo CPF existe na página

    function formatarCPF(cpfPosiciona) {
        cpf = cpf.replace(/\D/g, ""); // Remove tudo que não for número
        if (cpf.length === 11) { // Garante que tenha 11 dígitos antes de formatar
            return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4");
        }
        return cpfPosiciona;
    }

    // Formata o CPF ao carregar a página (caso já esteja preenchido)
    cpfInput.value = formatarCPF(cpfInput.value);

    // Aplica a máscara enquanto o usuário digita
    cpfInput.addEventListener("input", function () {
        cpfInput.value = formatarCPF(cpfInput.value);
    });
});


