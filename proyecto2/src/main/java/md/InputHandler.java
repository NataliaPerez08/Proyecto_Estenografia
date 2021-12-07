package main.java.md;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class InputHandler{
    public void getImage(){
        try {
            BufferedImage img = ImageIO.read(new File("resources/img/planes.png"));
            int height = img.getHeight();
            System.out.print("LO encontre"+height);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public String getTextFile(){
        try {
            File text = new File("resources/text/test.txt");
            Scanner scnr = new Scanner(text);
            System.out.print("Lo encontre");
        } catch (Exception e) {
            System.out.print(e);
        }
        return "";
    }
}