package br.com.helpdev.libvalidadores;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * Created by demantoide on 10/11/16.
 */

public class CreditCard {

    public enum EnumCreditCard {
        INVALID("INVALID"),
        VISA("Visa"),
        MASTERCARD("Mastercard"),
        AMERICAN_EXPRESS("American Express"),
        EN_ROUTE("En Route"),
        DINERS_CLUB("Diner's CLub/Carte Blanche");

        private String nome;

        EnumCreditCard(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }
    }

    /**
     * Valid a Credit Card number
     */
    public static EnumCreditCard validateCC(String number) throws Exception {
        EnumCreditCard cardID = getCardID(number);
        if (cardID != EnumCreditCard.INVALID && validateCCNumber(number)) {
            return cardID;
        }
        return EnumCreditCard.INVALID;
    }


    public static EnumCreditCard getCardID(String number) {
        number = number.replaceAll(Pattern.quote("."),"").replaceAll(Pattern.quote("-"),"");
        EnumCreditCard valid = EnumCreditCard.INVALID;

        String digit1 = number.substring(0, 1);
        String digit2 = number.substring(0, 2);
        String digit3 = number.substring(0, 3);
        String digit4 = number.substring(0, 4);

        if (isNumber(number)) {
            /*
             * ----* VISA prefix=4* ---- length=13 or 16 (can be 15 too!?!
             * maybe)
             */
            if (digit1.equals("4")) {
                if (number.length() == 13 || number.length() == 16)
                    valid = EnumCreditCard.VISA;
            }
            /*
             * ----------* MASTERCARD prefix= 51 ... 55* ---------- length= 16
             */
            else if (digit2.compareTo("51") >= 0 && digit2.compareTo("55") <= 0) {
                if (number.length() == 16)
                    valid = EnumCreditCard.MASTERCARD;
            }
            /*
             * ----* AMEX prefix=34 or 37* ---- length=15
             */
            else if (digit2.equals("34") || digit2.equals("37")) {
                if (number.length() == 15)
                    valid = EnumCreditCard.AMERICAN_EXPRESS;
            }
            /*
             * -----* ENROU prefix=2014 or 2149* ----- length=15
             */
            else if (digit4.equals("2014") || digit4.equals("2149")) {
                if (number.length() == 15)
                    valid = EnumCreditCard.EN_ROUTE;
            }
            /*
             * -----* DCLUB prefix=300 ... 305 or 36 or 38* ----- length=14
             */
            else if (digit2.equals("36")
                    || digit2.equals("38")
                    || (digit3.compareTo("300") >= 0 && digit3.compareTo("305") <= 0)) {
                if (number.length() == 14)
                    valid = EnumCreditCard.DINERS_CLUB;
            }
        }
        return valid;

        /*
         * ----* DISCOVER card prefix = 60* -------- lenght = 16* left as an
         * exercise ...
         */

    }

    private static boolean isNumber(String n) {
        try {
            Double.valueOf(n);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param number
     * @return
     */
    public static boolean validateCCNumber(String number) {
        number = number.replaceAll(Pattern.quote("."),"").replaceAll(Pattern.quote("-"),"");
        try {
            /*
             * * known as the LUHN Formula (mod10)
             */
            int j = number.length();

            String[] s1 = new String[j];
            for (int i = 0; i < number.length(); i++)
                s1[i] = "" + number.charAt(i);

            int checksum = 0;

            for (int i = s1.length - 1; i >= 0; i -= 2) {
                int k = 0;

                if (i > 0) {
                    k = Integer.valueOf(s1[i - 1]) * 2;
                    if (k > 9) {
                        String s = "" + k;
                        k = Integer.valueOf(s.substring(0, 1))
                                + Integer.valueOf(s.substring(1));
                    }
                    checksum += Integer.valueOf(s1[i]) + k;
                } else
                    checksum += Integer.valueOf(s1[0]);
            }
            return ((checksum % 10) == 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * * For testing purpose** java CCUtils [credit card number] or java CCUtils
     * *
     */
    public static void main(String args[]) throws Exception {
        String aCard = "";

        if (args.length > 0) {
            aCard = args[0];
        } else {
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    System.in));
            System.out.print("Card number : ");
            aCard = input.readLine();
        }
        if (getCardID(aCard) != EnumCreditCard.INVALID) {
            System.out.println("This card is supported.");
            System.out.println("This a " + getCardID(aCard).getNome());
            System.out.println("The card number " + aCard + " is "
                    + (validateCCNumber(aCard) ? " good." : " bad."));
        } else {
            System.out.println("This card is invalid or unsupported!");
        }
    }
}
