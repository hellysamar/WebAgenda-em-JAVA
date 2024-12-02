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

/**
const dataBirth = document.querySelector('#ipt-date');

dataBirth.addEventListener('keypress', () => {
	let dataLenght = dataBirth.value.length
	
})
*/

const inputNome = document.querySelector('#ipt-nome');
const inputFone = document.querySelector('#ipt-fone');
const inputMail = document.querySelector('#ipt-mail');
const inputData = document.querySelector('#ipt-date');

// INCLUINDO MASCARA (00) 00000-0000 NO CAMPO TELEFONE
inputFone.addEventListener('keypress', () => {
  let foneLength = inputFone.value.length;
  let fone = inputFone.value;
  let isCell = fone.substring(5, 6);
  let TelLength = document.getElementById('ipt-fone');
  if (inputFone.value === '') {
    inputFone.value += '(';
  } else if (foneLength === 3) {
    inputFone.value += ') ';
  } 

  if (isCell === "9") { 
    TelLength.maxLength="15";
    //é celular
    if (foneLength === 10) {
      inputFone.value += '-';
    } 
  } else {
    TelLength.maxLength="14";
    //é residencial
    if (foneLength === 9) {
      inputFone.value += '-';
    }
  }
})

// INCLUINDO MASCARA DD/MM/AAAA NO CAMPO DATA
inputData.addEventListener('keypress', () => {
  let dataLength = inputData.value.length
  
  if (dataLength === 2 || dataLength === 5) {
    inputData.value += '/';
  }
})

// IMPEDINDO USO DE CARACTERES NUMERICOS E ESPECIAIS NO CAMPO NOME
// IMPEDINDO USO DE ALFABETO E CHAR ESPECIAIS NO CAMPO FONE E DATA
inputNome.addEventListener('keypress', (e) => {
  if(!checkCharNome(e)){
    e.preventDefault();
  }
})

inputFone.addEventListener('keypress', (e) => {
  if(!checkCharFone(e)) {
    e.preventDefault();
  }
})

inputData.addEventListener('keypress', (e) => {
  if(!checkCharFone(e)) {
    e.preventDefault();
  }
})

// funcao impede caracteres numericos e especiais
function checkCharNome(e){
  const char = String.fromCharCode(e.keyCode);

  pattern = '[a-zA-Z ]'
  
  if (char.match(pattern)) {
    return true;
  }
}

function checkCharFone(e){
  const char = String.fromCharCode(e.keyCode);

  pattern = '[0-9]'
  
  if (char.match(pattern)) {
    return true;
  }
}

function checkCharData(e){
  const char = String.fromCharCode(e.keyCode);

  pattern = '[0-9]'
  
  if (char.match(pattern)) {
    return true;
  }
}

// IMPEDE USUÁRIO DE VOLAR CARACTERES INVALIDOS
inputNome.addEventListener('paste', function() {
  const regex = new RegExp("^[a-zA-Z\b]+$");
  const self = this;
    
  setTimeout(() => {
    const text = self.value;
    
    if (!regex.test(text)) {
      self.value = '';
    }
  }, 10);
    
  alert('O campo Nome só permite Letras!')
});
    
inputFone.addEventListener('paste', function() {
  const regex = new RegExp("^[1-9\b]+$");
  const self = this;

  
  setTimeout(() => {
    const text = self.value;
    
    if (!regex.test(text)) {
      self.value = '';
    }
  }, 10);

  alert('O campo Telefone só permite Numeros!')
});

inputData.addEventListener('paste', function() {
  const regex = new RegExp("^[0-9\b]+$");
  const self = this;

  
  setTimeout(() => {
    const text = self.value;
    
    if (!regex.test(text)) {
      self.value = '';
    }
  }, 10);

  alert('O campo Aniversário só permite Números!')
});

// VALIDAR SE A DATA É VÁLIDA
function validarData(dt) {
  var data = dt.value;
  let bissexto = false;

  inputData.style.borderColor='#ffffff';
  data = data.replace(/\//g, "-");

  var dataArray = data.split("-");
  var dia = dataArray[0];
  var mes = dataArray[1];
  var ano = dataArray[2];

  console.log('o dia é ' + dia);
  console.log('o mês é ' + mes);
  console.log('o ano é ' + ano);

  var dataReal = new Date();

  console.log('a data no modelo é date ' + dataReal.getDate);
  console.log('a data no modelo é month ' + dataReal.getMonth()+1);
  console.log('a data no modelo é day' + dataReal.getDay);
  console.log('a data no modelo é fullYear' + dataReal.getFullYear());
  console.log('a data no modelo é ' + dataReal);
  
  // VERIFICA SE ANO É BISSEXTO
  if (ano % 4 === 0 && ano % 100 != 0 || ano % 400 === 0) {
    bissexto = true;
  } else {
    bissexto = false;
  }

  // VERIFICA SE O ANO É FUTURO
  if (ano > dataReal.getFullYear()) {
    alert('data invalida, o ano deve ser esse ou anterior ao atual!');
    inputData.style.borderColor='#ff0000';
  } 
    
  // VERIFICA SE O MÊS ESTÁ ENTRE 1 E 12
  if (mes > 12) {
    alert('O ano possui apenas 12 meses!');
    inputData.style.borderColor='#ff0000';
  }
  
  // VERIFICA SE O MÊS CONFIGURADO É FEV
  if ((bissexto && mes === 2) || (bissexto && mes === '02')) {
    // dia deve ser no máximo 29
    if (dia > 29) {
      alert('Esse mês tem apenas 29 dias!');
      inputData.style.borderColor='#ff0000';
    }
  } else if ((!bissexto && mes === 2) || (!bissexto && mes === '02')){
    // dia deve ser no máximo 28
    if (dia > 28) {
      alert('Esse mês tem apenas 28 dias!')
      inputData.style.borderColor='#ff0000';
    }
  }

  // VERIFICA A QUANTIDADE DE DIAS DE ACORDO COM OS MESES
  if (mes === '01' || mes === '03' || mes === '05' || mes === '07' || mes === '08' || mes === '10' || mes === '12'){
    // mês possui 31 dias
    if (dia > 31) {
      alert('Esse mês tem apenas 31 dias!')
      inputData.style.borderColor='#ff0000';
    }
  } else if (mes === '04' || mes === '06' || mes === '09' || mes === '11') {
    // mês possui 30 dias
    if (dia > 30) {
      alert('Esse mês tem apenas 30 dias!')
      inputData.style.borderColor='#ff0000';
    }
  }
}

function voltar() {
	window.history.back();
}