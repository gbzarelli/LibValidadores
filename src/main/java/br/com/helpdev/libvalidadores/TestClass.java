package br.com.helpdev.libvalidadores;

/**
 * Created by demantoide on 09/11/16.
 */

public class TestClass {
    public static void main(String... args) {
        System.out.println(CPFUtils.getCPF(true));
        System.out.println(CPFUtils.getCPF(false));

        System.out.println(CNPJUtils.getCNPJ(true));
        System.out.println(CNPJUtils.getCNPJ(false));

        System.out.println(CNPJUtils.validaCNPJ("85.995.875/0001-23"));
        System.out.println(CNPJUtils.validaCNPJ("04.566.433/0001-60"));
        System.out.println(CNPJUtils.validaCNPJ("78922745000103"));
        //**
        System.out.println(CNPJUtils.validaCNPJ("78922735000153"));
        System.out.println(CNPJUtils.validaCNPJ("111178922735000153"));
        System.out.println(CNPJUtils.validaCNPJ("11111111111111"));
    }
}
