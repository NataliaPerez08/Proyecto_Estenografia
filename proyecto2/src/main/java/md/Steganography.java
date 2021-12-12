package main.java.md;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Steganography{

    public int[] hideMessage(int[] image, String message){
        int[] newImg = Arrays.copyOf(image,image.length);
        String[] charArrayBinaryString = message.split(" "); // caracter en binario
        int i=0;
        if(message.length() < image.length){
            for (String sC : charArrayBinaryString) {
                byte tmp = Byte.parseByte(sC, 2);
                newImg[i] = image[i] & tmp;
                i++;
            }
        }
        return newImg;
    }
    public String getMessage(int[] image) {
        String msgBin="";
        int c=0;
        for (int i : image) {
            String biString = Integer.toBinaryString(i);
            int len = biString.length()-1;
            msgBin += biString.charAt(len);
            if(c++ == 8){
                msgBin+=" ";
                c=0;
            }
        }
        return msgBin;
    }
}