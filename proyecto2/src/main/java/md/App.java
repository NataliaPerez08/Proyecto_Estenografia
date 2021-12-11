package md;

import main.java.md.Converter;
import main.java.md.IOHandler;
import main.java.md.Steganography;

import java.awt.image.BufferedImage;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){ 
        IOHandler nHandler = new IOHandler();
        Converter converter = new Converter();
        Steganography steganography = new Steganography();
        BufferedImage img = nHandler.getImage();
        int[] pixels = converter.imageToBinary(img);

        String message = converter.textToBinary("NO");
        int[] prueba = steganography.hideMessage(pixels, message);

        String binMsg = steganography.getMessage(prueba);

        //System.out.println(message+"\n"+binMsg+"\n");
        //System.out.println(message+": mess"+converter.binaryToText(message));

        try{
            if(img != null){
                try{
                    nHandler.giveImage(img.getWidth(), img.getHeight(), prueba);
                }catch(Exception e){System.out.print(e);  }
            }
        }catch(Exception e){System.out.print(e);}
    }
}