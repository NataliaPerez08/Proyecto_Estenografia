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
    public BufferedImage getImage(String path){
        try {
            BufferedImage img = ImageIO.read(new File(path));
            return img;
        } catch (Exception e) {
            System.out.print(e);
        }
        return null;
    }

    public String getTextFile(String path){
        String msg = "";
        try {
            File text = new File(path);
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

    public String processString(String text){
        StringBuilder sb = new StringBuilder();
        text = text.trim();
        text = text.toUpperCase();
        int len = text.length();
        for(int i=0; i < len;i++){
            char c = text.charAt(i);
            if(c >= 65 && c <= 90){//Entre 65 y 90
                sb.append(c);
            }  
        }
        return sb.toString();
    }

    public boolean giveTextFile(String message, String path){ // Mensaje recuperado
        try{
        File file = new File(path);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, false);
            fw.write(message);
            fw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean giveImage(int width, int height, int[] data, String path) throws IOException{
        Converter cv = new Converter(); 
        return cv.byteToImage(path, width, height, data);
    }
}