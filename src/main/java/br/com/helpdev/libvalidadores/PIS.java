package br.com.helpdev.libvalidadores;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by demantoide on 10/11/16.
 */

public class PIS {

    public static boolean validatePIS(String valor) {
        valor = valor.trim().replaceAll(Pattern.quote("."), "")
                .replaceAll(Pattern.quote("-"), "")
                .replaceAll(Pattern.quote("/"), "");

        int liTamanho = 0;
        StringBuffer lsAux = null;
        StringBuffer lsMultiplicador = new StringBuffer("3298765432");
        int liTotalizador = 0;
        int liResto = 0;
        int liMultiplicando = 0;
        int liMultiplicador = 0;
        boolean lbRetorno = true;
        int liDigito = 99;
        lsAux = new StringBuffer().append(valor);
        liTamanho = lsAux.length();
        if (liTamanho != 11) {
            lbRetorno = false;
        }
        if (lbRetorno) {
            for (int i = 0; i < 10; i++) {
                liMultiplicando = Integer.parseInt(lsAux.substring(i, i + 1));
                liMultiplicador = Integer.parseInt(lsMultiplicador.substring(i, i + 1));
                liTotalizador += liMultiplicando * liMultiplicador;
            }
            liResto = 11 - liTotalizador % 11;
            liResto = liResto == 10 || liResto == 11 ? 0 : liResto;
            liDigito = Integer.parseInt("" + lsAux.charAt(10));
            lbRetorno = liResto == liDigito;
        }
        return lbRetorno;
    }

    public static String getPIS(boolean formatado) {
        Random r = new Random();
        int num = 0;

        int n = 9;
        int a = r.nextInt(n);
        int b = r.nextInt(n);
        int c = r.nextInt(n);
        int d = r.nextInt(n);
        int e = r.nextInt(n);
        int f = r.nextInt(n);
        int g = r.nextInt(n);
        int h = r.nextInt(n);
        int i = r.nextInt(n);
        int j = r.nextInt(n);

        int soma = (3 * a) + (2 * b) + (9 * c) + (8 * d) + (7 * e) + (6 * f) + (5 * g) + (4 * h) + (3 * i) + (2 * j);
        int resto = soma % 11;
        int digitoVerificador = 11 - resto;
        if (digitoVerificador == 10 || digitoVerificador == 11) {
            digitoVerificador = 0;
        }

        StringBuilder numFormatado = new StringBuilder();
        numFormatado.append(a).append(b).append(c);
        if (formatado) numFormatado.append(".");
        numFormatado.append(d).append(e).append(f).append(g).append(h);
        if (formatado) numFormatado.append(".");
        numFormatado.append(i).append(j);
        if (formatado) numFormatado.append("-");
        numFormatado.append(digitoVerificador);

        return numFormatado.toString();
    }

}
