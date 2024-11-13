/**
 * Validações de confirmação
 * @author Hellysamar Araujo
 */

function confirmar(id) {
	let resposta = confirm("Deseja Excluir o contato?");
	if (resposta === true) {
		//alert(id)
		window.location.href = "excluir_contato?id=" + id;
	}
}