package md;

import main.java.md.Converter;
import main.java.md.InputHandler;
import java.awt.image.BufferedImage;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){
        InputHandler nHandler = new InputHandler();
        Converter converter = new Converter();
        BufferedImage img = nHandler.getImage();
        int[] pixels = converter.imageToBinary(img);
        System.out.print("(Valores RGB en 0,0: "+img.getRGB(0, 0)+")");
        try{
        converter.textToImage("output/newImage.png", img.getWidth(), img.getHeight(), pixels);
        }catch(Exception e){}
    }
}
