document.getElementById("btnExcluir").addEventListener("click", function () {
    const pessoaId = document.getElementById("pessoaId").value;

    if (confirm("Tem certeza que deseja excluir esta pessoa?")) {
        fetch(`/pessoa/excluirPessoa/${pessoaId}`, {
            method: "DELETE"
        })
        .then(response => {
            if (response.ok) {
                alert("Pessoa excluída com sucesso!");
                window.location.href = "/cadastroPessoa";
            } else {
                alert("Erro ao excluir pessoa.");
            }
        })
        .catch(error => console.error("Erro:", error));
    }
});
