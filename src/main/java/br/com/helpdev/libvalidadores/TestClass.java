package br.com.helpdev.libvalidadores;

/**
 * Created by demantoide on 09/11/16.
 */

public class TestClass {
    public static void main(String... args) {
//        System.out.println(CPF.getCPF(true));
//        System.out.println(CPF.getCPF(false));
//
//        System.out.println(CNPJ.getCNPJ(true));
//        System.out.println(CNPJ.getCNPJ(false));
//
//        System.out.println(CNPJ.validateCNPJ("85.995.875/0001-23"));
//        System.out.println(CNPJ.validateCNPJ("04.566.433/0001-60"));
//        System.out.println(CNPJ.validateCNPJ("78922745000103"));
//        //**
//        System.out.println(CNPJ.validateCNPJ("78922735000153"));
//        System.out.println(CNPJ.validateCNPJ("111178922735000153"));
//        System.out.println(CNPJ.validateCNPJ("11111111111111"));

        System.out.println(RENAVAM.getRENAVAM());
        System.out.println(RENAVAM.validateRENAVAM("523498586"));
        System.out.println(RENAVAM.validateRENAVAM("523737920"));
        System.out.println(RENAVAM.validateRENAVAM("766149064"));
        System.out.println(RENAVAM.validateRENAVAM("00823519853"));
    }
}
