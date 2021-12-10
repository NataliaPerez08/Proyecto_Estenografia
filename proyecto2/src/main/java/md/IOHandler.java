package main.java.md;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import javax.imageio.ImageIO;

import main.java.md.Converter;


public class IOHandler{
    private static String PATH_TEXT_INPUT = "resources/text/test.txt";
    private static String PATH_TEXT_OUTPUT = "output/text/test.txt";
    private static String PATH_IMAGE_OUTPUT="output/img/newImage.png";
    private static String PATH_IMAGE_INPUT ="resources/img/diez.png";


    public BufferedImage getImage(){
        try {
            BufferedImage img = ImageIO.read(new File(PATH_IMAGE_INPUT));
            return img;
        } catch (Exception e) {
            System.out.print(e);
        }
        return null;
    }

    public String getTextFile(){
        try {
            File text = new File(PATH_TEXT_INPUT);
            System.out.print("Lo encontre");
        } catch (Exception e) {
            System.out.print(e);
        }
        return "";
    }

    public boolean giveTextFile(String message){ // Mensaje recuperado
        return false;
    }

    public boolean giveImage(int width, int height, int[] data){
        try{
            Converter cv = new Converter(); 
            return cv.byteToImage(PATH_IMAGE_OUTPUT, width, height, data);
        }catch(Exception e){return false;}
    }
}