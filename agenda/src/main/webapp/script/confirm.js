/**
 * Validacoes de confirmacao
 * @author Hellysamar Araujo
 */

function confirmar(id) {
	let resposta = confirm("Deseja Excluir o contato?");
	if (resposta === true) {
		window.location.href = "excluir_contato?id=" + id;
	}
}

