package main.java.md;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;

import javax.imageio.ImageIO;
import java.io.IOException;

import main.java.md.Converter;

/** Esta clase se encarga de manejar la entrada y salida del programa */
public class IOHandler{
    /**
     * Este método recupera la imagen indicada en la ruta que se paso como parámetro
     * @param path
     * @return BufferedImage
     */
    public BufferedImage getImage(String path){
        try {
            BufferedImage img = ImageIO.read(new File(path));
            return img;
        } catch (FileNotFoundException fnfe){
            System.out.println("File was not found.");
        } catch (IllegalArgumentException iae){
            System.out.println("Image path not entered.");
        } catch (IOException ioe){
            System.out.println("An error occurred while reading the file.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred.");
        }
        return null;
    }

    /**
     * Este método recupera el texto de un archivo y lo regresa como cadena
     * @param path
     * @return String
     */
    public String getTextFile(String path){
        String msg = "";
        try {
            File text = new File(path);
            Scanner sc = new Scanner(text);
            while (sc.hasNextLine()){
                msg += sc.nextLine();
            }
            sc.close();
        } catch (FileNotFoundException fnfe){
            System.out.println("File was not found.");
        } catch (IllegalArgumentException iae){
            System.out.println("File path not entered.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred");
        }
        return msg;
    }

    /**
     * Este método procesa el texto recuperado de un archivo, es decir que elimina
     * todos lo caracteres especiales y lo convierte a mayuscula.
     * @param text
     * @return String con caracteres válidos, es decir [A-Z]
     */
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
    /**
     * Este método indica si el archivo fue creado
     * @param message (texto recuperado)
     * @param path (Donde será guardado el nuevo archivo)
     * @return boolean se creo el archivo
     */
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
        } catch (FileNotFoundException fnfe){
            System.out.println("File was not found.");
        } catch (IllegalArgumentException iae){
            System.out.println("File path not entered.");
        } catch(IOException ioe){
            System.out.println("An error occurred while writing the file.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred.");
        }
        return false;
    }
    /**
     * Este método indica si la imagen fue creada
     * @param width (Ancho de la imagen)
     * @param height (Altura de la imagen)
     * @param data  (Arreglo que contiene los pixeles de la imagem)
     * @param path  (Donde se guardara la imagen)
     * @return boolean (Si se ha creado la imagen)
     * @throws IOException
     */
    public boolean giveImage(int width, int height, int[] data, String path) throws IOException{
        Converter cv = new Converter(); 
        return cv.byteToImage(path, width, height, data);
    }
}
