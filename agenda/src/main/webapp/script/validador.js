/**
 * Validador de campos obrigatórios
 * @author Hellysamar Araujo
 */

function validar() {
	let nome = formContato.name.value;
	let fone = formContato.fone.value;
	let email = formContato.mail.value;
	let aniversario = formContato.birth.value;
	/**
	var str = aniversario;
	var ano = Number(str.substring(0, 4));
	var mes = Number(str.substring(4, 6));
	var dia = Number(str.substring(6));
	
	aniversario = ano + (mes - 1) + dia;
	var output = new Date(ano, mes - 1, dia).toLocaleDateString('pt-BR');
	console.log(output);
	*/
	
	if(nome === "") {
		alert('Preencha o campo Nome!');
		formContato.name.focus();
		return false;
	} else if (fone === "") {
		alert('Preencha o campo Telefone!');
		formContato.fone.focus();
		return false;
	} else {
		document.forms["formContato"].submit();
	}
}