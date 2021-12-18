package main.java.md;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.awt.image.BufferedImage;

import main.java.md.Converter;

/** Esta clase se encarga de ocultar el mensaje en imagen 
 * y recuperar el mensaje de la imagen */
public class Steganography{
    /** Permite a hacer conversiones desde esta clase */
    private Converter cv = new Converter();

    /**
     * Este método se encarga de ocultar el mensaje en la imagen.
     * Un caracter se oculta en dos pixeles
     * @param img (En la cual se quiere ocultar el mensaje)
     * @param message  (El mensaje que se quiere ocultar en binario)
     * @return int[] Arreglo de pixeles de la nueva imagen que tiene el mensaje oculto
     */
    public int[] hideMessage(BufferedImage img, String message){
        int[] pixels = cv.imageToBinary(img);
        //Valida que se tenga sufienciente espacio
        if(message.length() > pixels.length/2){
            System.out.print("Not enough space");
            return pixels;
        }

        int[] newImg = Arrays.copyOf(pixels,pixels.length);
        String[] charBinStrings = message.split(" ");
        int len = charBinStrings.length/2;
        for(int k=0; k < len; k++){
            String tmp = charBinStrings[k];
            int lenChar = tmp.length();
            String firstHalf = tmp.substring(0, lenChar/2);
            String secondHalf = tmp.substring(lenChar/2,lenChar);
            int edit = handlesinglepixel(pixels[k], firstHalf);
            int edit2 = handlesinglepixel(pixels[k+1], secondHalf);
            newImg[k] = edit;
            newImg[k+1] = edit2;
        }
        return newImg;
    }
    /**
     * Método auxiliar que toma un pixel y la mitad de la cadena en binario
     * de un caracter y lo envia a otro método auxiliar que cambia el bit menos 
     * significativo.
     * @param pixel
     * @param half
     * @return int (El nuevo valor del pixel)
     */
    private int handlesinglepixel(int pixel, String half) {
        String newNumber =chageLSB(pixel, half);
        return Integer.parseInt(newNumber,2);
   }
   /**
    * Método auxiliar que toma un pixel y la mitad de la cadena en binario
     * de un caracter y cambia el bit menos significativo de los canales del pixel
    * @param pixel
    * @param binString
    * @return String (valor del pixel en cadena en binario)
    */
   private String chageLSB(int pixel,String binString){
        int alpha = (pixel >> 24) & 0xff;
        int red   = (pixel >> 16) & 0xff;
        int green = (pixel >>  8) & 0xff;
        int blue  = (pixel      ) & 0xff;
        String alphaString = Integer.toBinaryString(alpha);
        String redString = Integer.toBinaryString(red);
        String greenString = Integer.toBinaryString(green);
        String blueString = Integer.toBinaryString(blue);
        if(binString.length() == 4){
            alphaString = appendBin(alphaString, binString.charAt(0));
            redString = appendBin(redString, binString.charAt(1));
            greenString = appendBin(greenString, binString.charAt(2));
            blueString = appendBin(blueString, binString.charAt(3));
        }
        return alphaString+redString+greenString+blueString;
   }
   /**
    * Método auxiliar que toma un canal en binario y cambia el LSB
    * @param pixel
    * @param binString
    * @return String (valor del canal en cadena en binario)
    */
   private String appendBin(String bin, char c){
        String auxBin="";
        auxBin = bin.substring(0,bin.length()-1)+c;
        return auxBin;
    }
   /**
    * Método que recupera el mensaje de la imagen
    * @param image (arreglo de pixeles de la imagen)
    * @return String (El mensaje)
    */
   public String getMessage(int[] image) {
        String aux="";
        StringBuilder msg = new StringBuilder();

        int len = image.length;
        for (int i = 0; i < len/2; i++) {
            aux += handleTwopixel(i,i+1);
            if(aux.length() == 8){ // la cadena aux de LSBs puede tener un caracter  
                int tmp = Integer.valueOf(aux,2);
                String slp = validChar((char) tmp);
                msg.append(slp);
                aux = "";
            }
        }
        return msg.toString();
    }

    /**
     * Método auxiliar que concatena LSB de dos pixeles
     * @param pixel
     * @param pixel2
     * @return String (LSB de dos pixeles)
     */
    private String handleTwopixel(int pixel,int pixel2) {
        return handlesinglepixel(pixel)+handlesinglepixel(pixel2);
    }

    /**
     * Método auxiliar que verifica que el caracter recibido sea valido
     * @param c
     * @return String (Caracter entre 65 y 90 , es decir, [A-Z])
     */
    private String validChar(char c){
        if (c >= 65 && c <= 90){
            return c+"";
        }
        return "";
    }
    
    /**
     * Método auxiliar que recupera LSB de los canales del pixel
     * @param pixel
     * @return String (LSB del pixel)
     */
    private String handlesinglepixel(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red   = (pixel >> 16) & 0xff;
        int green = (pixel >>  8) & 0xff;
        int blue  = (pixel      ) & 0xff;
        String alphaString = Integer.toBinaryString(alpha);
        String redString = Integer.toBinaryString(red);
        String greenString = Integer.toBinaryString(green);
        String blueString = Integer.toBinaryString(blue);

        String a = recoverBin(alphaString);
        String r = recoverBin(redString);
        String g = recoverBin(greenString);
        String b = recoverBin(blueString);
        return a+r+g+b;
    }
    /**
     * Método auxiliar que regresa el ultimo bit dada una cadena binaria
     * @param bin
     * @return String (último bit de cadena binaria)
     */
    private String recoverBin(String bin){
        String auxBin="";
        auxBin = bin.charAt(bin.length()-1)+"";
        return auxBin;
    }
}
