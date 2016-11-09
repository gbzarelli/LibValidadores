package br.com.helpdev.libvalidadores;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by demantoide on 09/11/16.
 */

public class CNPJUtils {

    public static String getCNPJ(boolean formatado) {

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

        int n9 = 0;
        int n10 = 0;
        int n11 = 0;
        int n12 = 1;

        int d1 = n12 * 2 + n11 * 3 + n10 * 4 + n9 * 5 + n8 * 6 + n7 * 7 + n6 * 8 + n5 * 9 + n4 * 2 + n3 * 3 + n2 * 4 + n1 * 5;

        d1 = 11 - (d1 % 11);

        if (d1 >= 10) {
            d1 = 0;
        }

        int d2 = d1 * 2 + n12 * 3 + n11 * 4 + n10 * 5 + n9 * 6 + n8 * 7 + n7 * 8 + n6 * 9 + n5 * 2 + n4 * 3 + n3 * 4 + n2 * 5 + n1 * 6;
        d2 = 11 - (d2 % 11);

        if (d2 >= 10) {
            d2 = 0;
        }

        StringBuilder result = new StringBuilder();
        result.append(n1).append(n2);
        if (formatado) result.append(".");
        result.append(n3).append(n4).append(n5);
        if (formatado) result.append(".");
        result.append(n6).append(n7).append(n8);
        if (formatado) result.append("/");
        result.append(n9).append(n10).append(n11).append(n12);
        if (formatado) result.append("-");
        result.append(d1).append(d2);

        return result.toString();

    }

    public static boolean validaCNPJ(String cnpj) {
        if (cnpj.contains(".") || cnpj.contains("-") || cnpj.contains("/")) {
            cnpj = cnpj.replaceAll(Pattern.quote("."), "")
                    .replaceAll(Pattern.quote("-"), "")
                    .replaceAll(Pattern.quote("/"), "");
        }


        if (cnpj.length() != 14 || cnpj.matches("^(\\d)\\1*$")) {
            return false;
        }

	   /* Valida Dv's */
        String cnpj_calc = cnpj.substring(0, 12);

        char[] chr_cnpj = cnpj.toCharArray();  

	   /* Primeira parte */
        int soma = 0, dig = 0;

        for (int i = 0; i < 4; i++) {
            if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
            }
        }
        for (int i = 0; i < 8; i++) {
            if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
                soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
            }
        }
        dig = 11 - (soma % 11);
        cnpj_calc += (dig == 10 || dig == 11) ?
                "0" : Integer.toString(dig);  
	
	   /* Segunda parte */
        soma = 0;
        for (int i = 0; i < 5; i++) {
            if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
            }
        }

        for (int i = 0; i < 8; i++) {
            if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
                soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
            }
        }

        dig = 11 - (soma % 11);
        cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

        if (cnpj.equals(cnpj_calc)) {
            return true;
        }

        return false;
    }
}
