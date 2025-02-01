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






