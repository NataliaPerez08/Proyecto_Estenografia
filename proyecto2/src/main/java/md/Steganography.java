package main.java.md;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.awt.image.BufferedImage;

import main.java.md.Converter;

public class Steganography{
    Converter cv = new Converter();

    public int[] hideMessage(BufferedImage img, String message){        
        int[] pixels = cv.imageToBinary(img);
        int[] newImg = Arrays.copyOf(pixels,pixels.length);
        message = message.replaceAll(" ", "");
        int cont=0;
        int len = message.length();
        for(int k=0; k < len; k++){
            char c = message.charAt(k);
            if(++cont==4) cont=0;
            int edit = handlesinglepixel(pixels[k],c,cont);
            //System.out.println(edit);
            newImg[k] = edit;
        }
        return newImg;
    }
    
    public int handlesinglepixel(int pixel,char c, int cont) {
        int alpha = (pixel >> 24) & 0xff;
        int red   = (pixel >> 16) & 0xff;
        int green = (pixel >>  8) & 0xff;
        int blue  = (pixel      ) & 0xff;
        String alphaString = Integer.toBinaryString(alpha);
        String redString = Integer.toBinaryString(red);
        String greenString = Integer.toBinaryString(green);
        String blueString = Integer.toBinaryString(blue);

        String newNumber="";
        switch (cont) {
            case 0:
                newNumber+=appendBin(alphaString, c);
            break;
            case 1:
                newNumber+=appendBin(redString, c);
            break;
            case 2:
                newNumber+=appendBin(greenString, c);
            break;
            case 3:
                newNumber+=appendBin(blueString, c);
            break; 
        }
        return Integer.parseInt(newNumber,2);
   }

   public String appendBin(String bin, char c){
       String auxBin="";
    if(Integer.valueOf(bin) == 0){
        auxBin = bin+c;
    }else{
        auxBin = bin.substring(0,7)+c;
    }
    return auxBin;
}
   
   public String getMessage(int[] image) {
       String aux="";
        StringBuilder msg = new StringBuilder();
        int c=0;
        for (int i : image) {
            aux += handlesinglepixel(i);
            if(++c==1){
                System.out.print("++"+c+"++");
                int tmp = Integer.valueOf(aux);
                msg.append(validChar((char) tmp));
                c=0;
            }
        }
        return msg.toString();
    }

    public String validChar(char c){
        if (c >= 65 && c <= 90){
            return c+"";
        }
        return "";
    }

    public String handlesinglepixel(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red   = (pixel >> 16) & 0xff;
        int green = (pixel >>  8) & 0xff;
        int blue  = (pixel      ) & 0xff;
        String alphaString = Integer.toBinaryString(alpha);
        String redString = Integer.toBinaryString(red);
        String greenString = Integer.toBinaryString(green);
        String blueString = Integer.toBinaryString(blue);

        String a = appendBin(alphaString);
        String r = appendBin(redString);
        String g = appendBin(greenString);
        String b = appendBin(blueString);
        return a+r+g+b;
    }

    public String appendBin(String bin){
        String auxBin="";
        auxBin = bin.charAt(bin.length()-1)+"";
        return auxBin;
 }
}