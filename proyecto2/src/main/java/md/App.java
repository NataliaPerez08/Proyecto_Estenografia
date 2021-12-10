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
        try{
            BufferedImage img = nHandler.getImage();
            if(img != null){
                int[] pixels = converter.imageToBinary(img);

                String message = converter.textToBinary("No idea");
                int[] prueba = steganography.hideMessage(pixels, message);
                try{
                    nHandler.giveImage(img.getWidth(), img.getHeight(), prueba);
                }catch(Exception e){System.out.print(e);  }
            }
        }catch(Exception e){System.out.print(e);}
    }
}
