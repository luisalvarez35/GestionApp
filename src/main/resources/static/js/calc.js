var valorVisor = 0;
var numeroA;
var numeroB;
var operacao;


function botao(dado) {
    var auxiliar = document.getElementById("visor").value; // auxiliar recebe o valor pressionado no visor
    document.getElementById("visor").value = auxiliar + dado; // visor recebe o valor de auxiliar e concatena com dado


    valorVisor = document.getElementById("visor").value = auxiliar + dado;
    //document.getElementById("historico").innerHTML = agora.getHours();

}


function btn_soma(caracter){
    numeroA = valorVisor;
    operacao = "+";
    
    limpar();
    //document.getElementById("historico").innerHTML += operacao;
}

function btn_subtrai(caracter){
  numeroA = valorVisor;
  operacao = "-";
  
  limpar();
}

function btn_multiplica(caracter){
  numeroA = valorVisor;
  operacao = "*";
  
  limpar();
}

function btn_divide(caracter){
  numeroA = valorVisor;
  operacao = "/";
  
  limpar();
}

function reset() {
    // limpar visor
    document.getElementById('visor').value = '';
    valorVisor = 0;
    operacao = "";
}
function limpar(){
    document.getElementById('visor').value = '';
    
}

function btn_igual(){
    numeroB = valorVisor;
    calcular();
}

function calcular() {
    // faz o calculo, pega o resultado e colocar no visor
    //document.getElementById('visor').value = eval(resultado);

    //document.getElementById('visor').value = resultado;
    //document.getElementById('visor').value = eval(valorVisor);

    var total = 0;
    var ultimoTotal = 0;
      switch(operacao){
        case "+":
          total = parseFloat(numeroA) + parseFloat(numeroB);
          break;
        case "-":
            total = parseFloat(numeroA) - parseFloat(numeroB);
            break;
        case "*":
          total = parseFloat(numeroA) * parseFloat(numeroB);
          break;
        case "/":
          total = parseFloat(numeroA) / parseFloat(numeroB);
          break;
      }
      ultimoTotal = total;
      reset();
      document.getElementById('visor').value = total;
      valorVisor = ultimoTotal;

}

function showcalc(){
    if(document.getElementById('calc').style.visibility == 'visible')
    {
        document.getElementById('calc').style.visibility = 'hidden';
    }else {
        document.getElementById('calc').style.visibility = 'visible'
    }
}

function hidecalc(){
    if(document.getElementById('calc').style.visibility == 'visible')
    {
        document.getElementById('calc').style.visibility = 'hidden';
    }

}