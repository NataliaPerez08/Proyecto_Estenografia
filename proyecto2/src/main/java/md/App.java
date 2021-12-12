package md;

import main.java.md.Converter;
import main.java.md.IOHandler;
import main.java.md.Steganography;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

/**
 * Hello world!
 *
 */
public class App {
    static IOHandler nHandler = new IOHandler();
    static Converter converter = new Converter();
    static Steganography steganography = new Steganography();
    private static String PATH_TEXT_INPUT = "resources/text/msg.txt";
    private static String PATH_TEXT_OUTPUT = "output/text/newmsg.txt";
    private static String PATH_IMAGE_OUTPUT="output/img/newImage.png";
    private static String PATH_IMAGE_INPUT ="resources/img/cuadro.png";

    public static void main( String[] args ){ 

        BufferedImage img = nHandler.getImage(PATH_IMAGE_INPUT);
        int[] pixels = converter.imageToBinary(img);

        String message = converter.textToBinary("NO");
        int[] prueba = steganography.hideMessage(pixels, message);

        //String binMsg = steganography.getMessage(prueba);

        //System.out.println(message+"\n"+binMsg+"\n");
        //System.out.println(message+": mess"+converter.binaryToText(message));

        try{
            if(img != null){
                try{
                    nHandler.giveImage(img.getWidth(), img.getHeight(), prueba, PATH_IMAGE_OUTPUT);
                }catch(Exception e){System.out.print(e);  }
            }
        }catch(Exception e){System.out.print(e);}
    }
}