package br.com.helpdev.libvalidadores;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by demantoide on 09/11/16.
 */

public class CpfUtils {

    public static boolean validarCPF(String cpf) {
        if (cpf.contains(".") || cpf.contains("-")) {
            cpf = cpf.replaceAll(Pattern.quote("."), "").replaceAll(Pattern.quote("-"), "");
        }

        int i, j, k;
        int soma;
        int digito;
        int numeroAtual;
        String numero;
        if (cpf.length() != 11) {
            return false;
        }
        numero = cpf.substring(0, 9);
        for (j = 1; j < 3; j++) {
            k = 2;
            soma = 0;
            for (i = (numero.length() - 1); i >= 0; i--) {
                numeroAtual = Integer.parseInt(String.valueOf(numero.charAt(i)));
                soma = soma + numeroAtual * k;
                k = k + 1;
            }
            digito = 11 - (soma % 11);
            if (digito >= 10) {
                digito = 0;
            }
            numero = numero + digito;
        }

        return cpf.equals(numero);
    }

    public static String geraCPF() {
        ArrayList<Integer> listaAleatoria = new ArrayList<Integer>();
        ArrayList<Integer> listaNumMultiplicados = new ArrayList<Integer>();

        //Primeiro executamos os metodos de geracao
        for (int i = 0; i < 9; i++) {
            listaAleatoria.add((int) (Math.random() * 10));
        }

        /*PRIMEIRO DIGITO*/
        int primeiroDigito;
        int totalSomatoria = 0;
        int restoDivisao;
        int peso = 10;

        //Para cada item na lista multiplicamos seu valor pelo seu peso
        for (int item : listaAleatoria) {
            listaNumMultiplicados.add(item * peso);

            peso--;
        }

        //Agora somamos todos os itens que foram multiplicados
        for (int item : listaNumMultiplicados) {
            totalSomatoria += item;
        }

        restoDivisao = (totalSomatoria % 11);

        //Se o resto da divisao for menor que 2 o primeiro digito sera 0, senao subtraimos o numero 11 pelo resto da divisao
        if (restoDivisao < 2) {
            primeiroDigito = 0;
        } else {
            primeiroDigito = 11 - restoDivisao;
        }

        //Apos gerar o primeiro digito o adicionamos a lista
        listaAleatoria.add(primeiroDigito);
        /**/


        /*SEGUNDO DIGITO*/
        listaNumMultiplicados = new ArrayList<Integer>();
        int segundoDigito;
        totalSomatoria = 0;
        peso = 11;

        //Para cada item na lista multiplicamos seu valor pelo seu peso (observe que com o aumento da lista o peso tambem aumenta)
        for (int item : listaAleatoria) {
            listaNumMultiplicados.add(item * peso);
            peso--;
        }

        //Agora somamos todos os itens que foram multiplicados
        for (int item : listaNumMultiplicados) {
            totalSomatoria += item;
        }

        restoDivisao = (totalSomatoria % 11);

        //Se o resto da divisao for menor que 2 o segundo digito sera 0, senao subtraimos o numero 11 pelo resto da divisao
        if (restoDivisao < 2) {
            segundoDigito = 0;
        } else {
            segundoDigito = 11 - restoDivisao;
        }

        //Apos gerar o segundo digito o adicionamos a lista
        listaAleatoria.add(segundoDigito);
        //***/

        String texto = "";

        for (int item : listaAleatoria) {
            texto += item;
        }

        return texto;
    }
}
