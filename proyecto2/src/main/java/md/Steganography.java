package main.java.md;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.awt.image.BufferedImage;

import main.java.md.Converter;

public class Steganography{
    Converter cv = new Converter();

    public int[] hideMessage(BufferedImage img, String message){
        String[] charArrayBinaryString = message.split(" "); // caracter en binario
        
        
        int[] pixels = cv.imageToBinary(img);
        int[] newImg = Arrays.copyOf(pixels,pixels.length);
        int w = img.getWidth();
        int h = img.getHeight();

        for(int j=0; j < h; j++){
            for(int i=0; i < w; i++){
                handlesinglepixel(i, j, pixels[j*w+i]);
            }
        }

        return newImg;
    }
    
    public void handlesinglepixel(int x, int y, int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red   = (pixel >> 16) & 0xff;
        int green = (pixel >>  8) & 0xff;
        int blue  = (pixel      ) & 0xff;
        String alphaString = Integer.toBinaryString(alpha);
        String redString = Integer.toBinaryString(red);
        String greenString = Integer.toBinaryString(green);
        String blueString = Integer.toBinaryString(blue);
        System.out.println(pixel+" alpha:"+alpha+" red:"+red+" green:"+green+" blue:"+blue);
        System.out.println(".... alpha: "+alphaString+"....");
        System.out.println("....red: "+redString+"....");
        System.out.println("....green: "+greenString+"....");
        System.out.println("....blue: "+blueString+"....");
        // Deal with the pixel as necessary...

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