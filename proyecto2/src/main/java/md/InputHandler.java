package main.java.md;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class InputHandler{
    public BufferedImage getImage(){
        try {
            BufferedImage img = ImageIO.read(new File("resources/img/diez.png"));
            return img;
        } catch (Exception e) {
            System.out.print(e);
        }
        return null;
    }

    public String getTextFile(){
        try {
            File text = new File("resources/text/test.txt");
            System.out.print("Lo encontre");
        } catch (Exception e) {
            System.out.print(e);
        }
        return "";
    }
}