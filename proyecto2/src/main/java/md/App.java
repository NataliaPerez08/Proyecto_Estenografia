package md;

import main.java.md.Converter;
import main.java.md.IOHandler;
import main.java.md.Steganography;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Clase principal
 *
 */
public class App {
    static IOHandler ioHandler = new IOHandler();
    static Converter converter = new Converter();
    static Steganography steganography = new Steganography();
    private static String PATH_TEXT_INPUT = "resources/text/";
    private static String PATH_TEXT_OUTPUT = "output/text/";
    private static String PATH_IMAGE_OUTPUT="output/img/";
    private static String PATH_IMAGE_INPUT ="resources/img/";

    public static void main( String[] args ){ 
        Scanner sc = new Scanner(System.in);
        System.out.println("Type h to hide or u to uncover  ");
        while(sc.hasNext()){
            String op = sc.nextLine();
            if (op.equals("h")) {
                System.out.println("Hide");
                System.out.println("The name of the file that contains the text to hide: "+PATH_TEXT_INPUT+":");
                String fileToHide = sc.nextLine();
        
                System.out.println("The name of the image file: "+PATH_IMAGE_INPUT+":");
                String imgToHide = sc.nextLine();
        
                System.out.println("The name of the resulting image file with the hidden data: "+PATH_IMAGE_OUTPUT+":");
                String imgResult = sc.nextLine();
        
                try{
                    String msg = ioHandler.getTextFile(PATH_TEXT_INPUT+fileToHide);
                    BufferedImage img = ioHandler.getImage(PATH_IMAGE_INPUT+imgToHide);
                    String binMsg = converter.textToBinary(msg);
                    int[] newImg = steganography.hideMessage(img, binMsg);
                    int h = img.getHeight();
                    int w = img.getWidth();
                    if(ioHandler.giveImage(w, h, newImg, (PATH_IMAGE_OUTPUT+imgResult))){
                        System.out.println("Success");
                    }
                }catch(Exception e){
                    System.out.println("An unexpected error occurred.");
                }
            }
            if (op.equals("u")) {
                System.out.println("Uncover");

                System.out.println("The name of the file with the image that contains the hidden data: "+PATH_IMAGE_INPUT+":");
                String nameImg = sc.nextLine();

                System.out.println("The name of the file in which the revealed text will be saved: "+PATH_TEXT_OUTPUT+":");
                String textResult = sc.nextLine();

                try{
                    BufferedImage imgToUncover = ioHandler.getImage(PATH_IMAGE_INPUT+nameImg);
                    int[] imgPixels = converter.imageToBinary(imgToUncover);
                    
                    String uncoverMsg =steganography.getMessage(imgPixels);
    
                    if(ioHandler.giveTextFile(uncoverMsg, (PATH_TEXT_OUTPUT+textResult))){
                        System.out.println("Success");
                    }
                }catch(Exception e){
                    System.out.println("An unexpected error occurred.");
                }
            }
            System.out.println("Type h to hide or u to uncover  ");
        }
    }
}