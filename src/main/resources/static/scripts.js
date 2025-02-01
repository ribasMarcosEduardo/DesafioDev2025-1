document.addEventListener("DOMContentLoaded", function () {
    preencherDados(); // Chama a função automaticamente ao carregar a página
});

function preencherDados() {
    fetch("https://randomuser.me/api/")
        .then(response => response.json())
        .then(data => {
            const user = data.results[0];

            if (!user) {
                console.error("Erro: Nenhum usuário encontrado na API.");
                return;
            }

            // Pegando os dados da API
            const nome = `${user.name.first} ${user.name.last}`;
            const email = user.email;
            const telefone = user.phone;

            // Preenchendo os campos na tela
            document.querySelector("input[name='nome']").value = nome;
            document.querySelector("input[name='email']").value = email;
            document.querySelector("input[name='telefone']").value = telefone;
        })
        .catch(error => console.error("Erro ao buscar dados da API:", error));
}

// minúsculo


    document.getElementById('usuario').addEventListener('input', function() {
        this.value = this.value.toLowerCase();
    });

setTimeout(function() {
    console.log("Tentando remover a mensagem de erro...");
    var errorAlert = document.getElementById('error-alert');
    if (errorAlert) {
        console.log("Mensagem de erro encontrada. Removendo...");
        errorAlert.remove();
    } else {
        console.log("Mensagem de erro não encontrada.");
    }
}, 5000);

// Remove a mensagem de sucesso após 5 segundos
setTimeout(function() {
    console.log("Tentando remover a mensagem de sucesso...");
    var successAlert = document.getElementById('success-alert');
    if (successAlert) {
        console.log("Mensagem de sucesso encontrada. Removendo...");
        successAlert.remove();
    } else {
        console.log("Mensagem de sucesso não encontrada.");
    }
}, 5000);




