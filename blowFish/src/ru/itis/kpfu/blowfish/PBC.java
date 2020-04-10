package ru.itis.kpfu.blowfish;

public class PBC {

    private String initVector;

    public String encodeInPbcMode(String text, String key){
        initVector = "1111111111111111";
        String[] textArr = text.split("(?<=\\G.{16})");
        String result = "";

        while (textArr[textArr.length-1].length() < 16){
            textArr[textArr.length-1] = 0 + textArr[textArr.length-1];
        }
        for (int i = 0; i < textArr.length; i++) {
            BlowfishEncode blowfishEncode = new BlowfishEncode();
            String res = blowfishEncode.encode(textArr[i], key);
            res = xor(res, initVector);
            initVector = textArr[i];
            result += res;
        }

        return result;
    }

    public String decodeInPbcMode(String text, String key){
        initVector = "1111111111111111";
        String[] textArr = text.split("(?<=\\G.{16})");
        String result = "";
        while (textArr[textArr.length-1].length() < 16){
            textArr[textArr.length-1] = 0 + textArr[textArr.length-1];
        }
        for (int i = 0; i < textArr.length; i++) {
            BlowfishDecode blowfishDecode = new BlowfishDecode();
            textArr[i] = xor(textArr[i], initVector);
            String res = blowfishDecode.decode(textArr[i], key).replaceFirst ("^0*", "");
            result += res;
            initVector = res;
        }
        return result;
    }

    private String xor(String a, String b)
    {
        a = hexToBin(a);
        b = hexToBin(b);
        String ans = "";
        for (int i = 0; i < a.length(); i++)
            ans += (char)(((a.charAt(i) - '0')
                    ^ (b.charAt(i) - '0'))
                    + '0');
        ans = binToHex(ans);
        return ans;
    }

    private String binToHex(String plainText)
    {
        long num = Long.parseUnsignedLong(plainText, 2);
        String hexa = Long.toHexString(num);
        while (hexa.length() < (plainText.length() / 4))

            // maintain output length same length
            // as input by appending leading zeroes.
            hexa = "0" + hexa;
        return hexa;
    }

    // Принимает 16ричную строку и возвращаетее в бинарном виде +
    private String hexToBin(String plainText)
    {
        String binary = "";
        Long num;
        String binary4B;
        int n = plainText.length();
        for (int i = 0; i < n; i++) {
            //возвращает строковое представление из 16 в 10 сс
            num = Long.parseUnsignedLong(
                    plainText.charAt(i) + "", 16);
            binary4B = Long.toBinaryString(num);
            // each value in hexadecimal is 4 bits in binary.
            binary4B = "0000" + binary4B;
            binary4B = binary4B.substring(binary4B.length() - 4);
            binary += binary4B;
        }
        return binary;
    }

}
