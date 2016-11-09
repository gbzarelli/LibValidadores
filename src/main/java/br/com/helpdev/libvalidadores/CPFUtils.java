package br.com.helpdev.libvalidadores;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by demantoide on 09/11/16.
 */

public class CPFUtils {

    public static boolean validaCPF(String cpf) {
        if (cpf.contains(".") || cpf.contains("-")) {
            cpf = cpf.replaceAll(Pattern.quote("."), "").replaceAll(Pattern.quote("-"), "");
        }

        int i, j, k;
        int soma;
        int digito;
        int numeroAtual;
        String numero;
        if (cpf.length() != 11 || cpf.matches("^(\\d)\\1*$")) {
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

    public static String getCPF(boolean formatado) {

        Random random = new Random();

        int n = 9;
        int n1 = random.nextInt(n);
        int n2 = random.nextInt(n);
        int n3 = random.nextInt(n);
        int n4 = random.nextInt(n);
        int n5 = random.nextInt(n);
        int n6 = random.nextInt(n);
        int n7 = random.nextInt(n);
        int n8 = random.nextInt(n);
        int n9 = random.nextInt(n);
        int d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;

        d1 = 11 - (d1 % 11);

        if (d1 >= 10) {
            d1 = 0;
        }

        int d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;

        d2 = 11 - (d2 % 11);

        if (d2 >= 10) {
            d2 = 0;
        }

        StringBuilder result = new StringBuilder();
        result.append(n1).append(n2).append(n3);
        if (formatado) result.append(".");
        result.append(n4).append(n5).append(n6);
        if (formatado) result.append(".");
        result.append(n7).append(n8).append(n9);
        if (formatado) result.append("-");
        result.append(d1).append(d2);

        return result.toString();

    }

}
