document.addEventListener("DOMContentLoaded", function () {
    const cpfInput = document.getElementById("cpf");

    cpfInput.addEventListener("input", function () {
        let cpf = cpfInput.value.replace(/\D/g, "");

        if (cpf.length > 3) {
            cpf = cpf.substring(0, 3) + "." + cpf.substring(3);
        }
        if (cpf.length > 7) {
            cpf = cpf.substring(0, 7) + "." + cpf.substring(7);
        }
        if (cpf.length > 11) {
            cpf = cpf.substring(0, 11) + "-" + cpf.substring(11, 13);
        }

        cpfInput.value = cpf.substring(0, 14);
    });
});
