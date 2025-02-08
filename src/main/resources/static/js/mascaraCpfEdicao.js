document.addEventListener("DOMContentLoaded", function () {
    const cpfInput = document.getElementById("cpf");

    function formatarCPF(cpf) {
        cpf = cpf.replace(/\D/g, "");
        if (cpf.length > 3) {
            cpf = cpf.replace(/(\d{3})/, "$1.");
        }
        if (cpf.length > 7) {
            cpf = cpf.replace(/(\d{3})\.(\d{3})/, "$1.$2.");
        }
        if (cpf.length > 11) {
            cpf = cpf.replace(/(\d{3})\.(\d{3})\.(\d{3})/, "$1.$2.$3-");
        }
        return cpf.substring(0, 14);
    }

    if (cpfInput) {
        cpfInput.value = formatarCPF(cpfInput.value);
        cpfInput.addEventListener("input", function () {
            this.value = formatarCPF(this.value);
        });
    }
});


