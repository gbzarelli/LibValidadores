package br.com.helpdev.libvalidadores;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Pattern;

/**
 * Created by demantoide on 10/11/16.
 */

public class CreditCard {

    public enum EnumCreditCard {
        VISA(new String[]{"4539", "4556", "4916", "4532", "4929", "40240071", "4485", "4716", "4"}),
        MASTERCARD(new String[]{"51", "52", "53", "54", "55"}),
        AMEX(new String[]{"34", "37"}),
        DISCOVER(new String[]{"6011"}),
        DINERS(new String[]{"300", "301", "302", "303", "36", "38"}),
        ENROUTE(new String[]{"2014", "2149"}),
        JCB(new String[]{"35"}),
        VOYAGER(new String[]{"8699"});

        String[] prefix;

        EnumCreditCard(String[] prefix) {
            this.prefix = prefix;
        }
    }


    public static String generateCreditCardNumber() {
        return generateCreditCardNumber(16);
    }

    static String generateCreditCardNumber(EnumCreditCard prefix) {
        return generateCreditCardNumber(prefix, 16);
    }


    public static String generateCreditCardNumber(int maxLength) {
        int randomArrayIndex = (int) Math.floor(Math.random() * EnumCreditCard.values().length);
        EnumCreditCard prefix = EnumCreditCard.values()[randomArrayIndex];
        return generateCreditCardNumber(prefix, maxLength);
    }


    public static String generateCreditCardNumber(EnumCreditCard prefix, int length) {
        if (prefix == EnumCreditCard.DINERS) {
            length = 14;
        }
        if (prefix == EnumCreditCard.AMEX || prefix == EnumCreditCard.ENROUTE) {
            length = 15;
        }

        int randomArrayIndex = (int) Math.floor(Math.random() * prefix.prefix.length);
        String ccnumber = prefix.prefix[randomArrayIndex];
        return generateCreditCardNumber(ccnumber, length);
    }

    static String generateCreditCardNumber(String prefix, int length) {
        String ccnumber = prefix;

        // generate digits
        while (ccnumber.length() < (length - 1)) {
            ccnumber += Double.valueOf(Math.floor(Math.random() * 10)).intValue();
        }

        // reverse number and convert to int

        String reversedCCnumberString = strrev(ccnumber);

        List<Integer> reversedCCnumberList = new ArrayList<>();
        for (int i = 0; i < reversedCCnumberString.length(); i++) {
            reversedCCnumberList.add(Integer.valueOf(String.valueOf(reversedCCnumberString.charAt(i))));
        }

        // calculate sum

        int sum = 0;
        int pos = 0;

        Integer[] reversedCCnumber = reversedCCnumberList
                .toArray(new Integer[reversedCCnumberList.size()]);
        while (pos < length - 1) {

            int odd = reversedCCnumber[pos] * 2;
            if (odd > 9) {
                odd -= 9;
            }

            sum += odd;

            if (pos != (length - 2)) {
                sum += reversedCCnumber[pos + 1];
            }
            pos += 2;
        }

        // calculate check digit

        int checkdigit = Double.valueOf(((Math.floor(sum / 10) + 1) * 10 - sum) % 10).intValue();
        ccnumber += checkdigit;

        return ccnumber;
    }

    private static boolean isNumber(String n) {
        try {
            double d = Double.valueOf(n);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static EnumCreditCard getCardID(String number) {
        number = number.trim().replaceAll(Pattern.quote("."), "")
                .replaceAll(Pattern.quote("-"), "")
                .replaceAll(Pattern.quote("/"), "");

        EnumCreditCard valid = null;

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
            /**
             * JCB(new String[]{"35"}),
             */
            else if (digit2.compareTo("35") == 0) {
                if (number.length() == 16)
                    valid = EnumCreditCard.JCB;
            }

            /**
             *DISCOVER(new String[]{"6011"}),
             */
            else if (digit4.compareTo("6011") == 0) {
                if (number.length() == 16)
                    valid = EnumCreditCard.DISCOVER;
            }
            /**
             VOYAGER(new String[]{"8699"});
             */
            else if (digit4.compareTo("8699") == 0) {
                if (number.length() == 15 || number.length() == 16)
                    valid = EnumCreditCard.VOYAGER;
            }
            /*
             * ----* AMEX prefix=34 or 37* ---- length=15
             */
            else if (digit2.equals("34") || digit2.equals("37")) {
                if (number.length() == 15)
                    valid = EnumCreditCard.AMEX;
            }
            /*
             * -----* ENROU prefix=2014 or 2149* ----- length=15
             */
            else if (digit4.equals("2014") || digit4.equals("2149")) {
                if (number.length() == 15)
                    valid = EnumCreditCard.ENROUTE;
            }
            /*
             * -----* DCLUB prefix=300 ... 305 or 36 or 38* ----- length=14
             */
            else if (digit2.equals("36")
                    || digit2.equals("38")
                    || (digit3.compareTo("300") >= 0 && digit3.compareTo("305") <= 0)) {
                if (number.length() == 14)
                    valid = EnumCreditCard.DINERS;
            }
        }
        return valid;

        /*
         * ----* DISCOVER card prefix = 60* -------- lenght = 16* left as an
         * exercise ...
         */

    }

    private static String strrev(String str) {
        if (str == null)
            return "";
        String revstr = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            revstr += str.charAt(i);
        }

        return revstr;
    }


    public static boolean isValidCreditCardNumber(String number) {
        number = number.trim().replaceAll(Pattern.quote("."), "")
                .replaceAll(Pattern.quote("-"), "")
                .replaceAll(Pattern.quote("/"), "");

        boolean isValid = false;

        try {
            String reversedNumber = new StringBuffer(number)
                    .reverse().toString();
            int mod10Count = 0;
            for (int i = 0; i < reversedNumber.length(); i++) {
                int augend = Integer.parseInt(String.valueOf(reversedNumber
                        .charAt(i)));
                if (((i + 1) % 2) == 0) {
                    String productString = String.valueOf(augend * 2);
                    augend = 0;
                    for (int j = 0; j < productString.length(); j++) {
                        augend += Integer.parseInt(String.valueOf(productString
                                .charAt(j)));
                    }
                }

                mod10Count += augend;
            }

            if ((mod10Count % 10) == 0) {
                isValid = true;
            }
        } catch (NumberFormatException e) {
        }

        return isValid;
    }

}
