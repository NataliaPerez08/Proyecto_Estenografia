package main.java.md;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;

import javax.imageio.ImageIO;
import java.io.IOException;

import main.java.md.Converter;


public class IOHandler{
    private static String PATH_TEXT_INPUT = "resources/text/test.txt";
    private static String PATH_TEXT_OUTPUT = "output/text/test.txt";
    private static String PATH_IMAGE_OUTPUT="output/img/newImage.png";
    private static String PATH_IMAGE_INPUT ="resources/img/cuadro.png";


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
        String msg = "";
        try {
            File text = new File(PATH_TEXT_INPUT);
            Scanner sc = new Scanner(text);
            while (sc.hasNextLine()){
                msg += sc.nextLine();
            }
            sc.close();
        } catch (Exception e) {
            System.out.print(e);
        }
        return msg;
    }

    public boolean giveTextFile(String message){ // Mensaje recuperado
        try{
        File file = new File(PATH_TEXT_OUTPUT);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(message);
            bw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean giveImage(int width, int height, int[] data) throws IOException{
        Converter cv = new Converter(); 
        return cv.byteToImage(PATH_IMAGE_OUTPUT, width, height, data);
    }
}