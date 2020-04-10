package ru.itis.kpfu.blowfish;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ECB ecb = new ECB();
        try {
            File file = new File("vectors.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] arr = line.split("        ");
                String text = arr[0];
                String key = arr[1];
                System.out.println("Кодируем:" + text);
                long m = System.currentTimeMillis();
                text = ecb.encodeInEcbMode(text, key);
                System.out.println("Время на кодировку: " + (double) (System.currentTimeMillis() - m));
                System.out.println("Закодировано: "+ text);
                long n = System.currentTimeMillis();
                System.out.println("-----------Раскодировано:" + ecb.decodeInEcbMode(text,key));
                System.out.println("Время на раскодировку: " + (double) (System.currentTimeMillis() - n));
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        PBC pbc = new PBC();
        try {
            File file = new File("vectors.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] arr = line.split("        ");
                String text = arr[0];
                String key = arr[1];
                System.out.println("Кодируем:" + text);
                long m = System.currentTimeMillis();
                text = pbc.encodeInPbcMode(text, key);
                System.out.println("Время на кодировку: " + (double) (System.currentTimeMillis() - m));
                System.out.println("Закодировано: "+ text);
                long n = System.currentTimeMillis();
                System.out.println("-----------Раскодировано:" + pbc.decodeInPbcMode(text,key));
                System.out.println("Время на раскодировку: " + (double) (System.currentTimeMillis() - n));
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
