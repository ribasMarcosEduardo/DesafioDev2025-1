console.log("Iniciando scripts.js...");

// Remove a mensagem de erro após 5 segundos
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