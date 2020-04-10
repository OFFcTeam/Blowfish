package ru.itis.kpfu.blowfish;

public class ECB {
    
    public String encodeInEcbMode(String text, String key){

        String[] textArr = text.split("(?<=\\G.{16})");
        String result = "";

        while (textArr[textArr.length-1].length() < 16){
            textArr[textArr.length-1] = 0 + textArr[textArr.length-1];
        }
        for (int i = 0; i < textArr.length; i++) {
            BlowfishEncode blowfishEncode = new BlowfishEncode();
            result += blowfishEncode.encode(textArr[i], key);
        }
        return result;
    }

    public String decodeInEcbMode(String text, String key){

        String[] textArr = text.split("(?<=\\G.{16})");
        String result = "";
        while (textArr[textArr.length-1].length() < 16){
            textArr[textArr.length-1] = 0 + textArr[textArr.length-1];
        }
        for (int i = 0; i < textArr.length; i++) {
            BlowfishDecode blowfishDecode = new BlowfishDecode();
            result += blowfishDecode.decode(textArr[i], key).replaceFirst ("^0*", "");
            System.out.println(result);
        }
        return result;
    }
}
