package main.java.md;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Steganography{

    public int[] hideMessage(int[] image, String message){
        int[] newImg = Arrays.copyOf(image,image.length);
        String[] charArrayBinaryString = message.split(" ");
        int i=0;
        for (String sC : charArrayBinaryString) {
            String biString = Integer.toBinaryString(image[i]);
            Long tmp = Long.parseLong(biString, 2);
            newImg[i] = tmp.intValue();
            i++;
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