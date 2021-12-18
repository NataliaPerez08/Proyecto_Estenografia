package main.java.md;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;

import java.awt.AWTError;
/** Esta clase se encarga de realizar conversiones, por ejemplo binario a texto */
public class Converter{
    /**
     *  Este método recibe una cadena de caracteres en binario y
     * lo cambia letras
     * @param message
     * @return String (Mensaje)
     */
    public String binaryToText(String message){
        StringBuilder uncoverMessage = new StringBuilder();
        String[] binaryLetters = message.split(" ");
        for (String s : binaryLetters) {
            int charCode = Integer.parseInt(s, 2);
            uncoverMessage.append((char)charCode);
        }
        return uncoverMessage.toString();
    }

     /**
      * Este método recibe un texto y convierte a cadena binaria
      * @param text
      * @return String (texto en cadena binaria)
      */
    public String textToBinary(String text){ 
        byte[] bytes = text.getBytes();
        return byteToBinary(bytes);
    }
    /**
     * Método auxiliar que convierte bytes en binario mediante desplazamiento de bits
     * @param bytes
     * @return
     */
    private String byteToBinary(byte[] bytes){
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for(int i=1; i <= 8; i++){
                if((val & 128) == 0){
                    binary.append(0);
                }else{
                    binary.append(1);
                }
                val<<=1;
            }
            binary.append(" ");
        }
        return binary.toString();
    }
    /**
     * Método que mediante la clase PixelGrabber regrese la información de los pixeles
     * en un arreglo de enteros
     * @param image
     * @return
     */
    public int[] imageToBinary(BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();
        int[] pixels = new int[width * height];
        PixelGrabber pgb = new PixelGrabber(image, 0, 0, width, height, pixels, 0, width);
        try{
            pgb.grabPixels();   
            return pixels;
        } catch (InterruptedException e) {
            System.err.println("interrupted waiting for pixels!");
        } catch(Exception e){
            System.out.println("An unexpected error occurred.");
        }
        return null;
    }

    /**
     * Método que convierte un arreglo de enteros o bytes a una imagen
     * @param path (Donde se guardara la imagen)
     * @param width (Ancho de la imagen)
     * @param height (Altura de la imagen)
     * @param data (arreglo de int o byte con el mensaje)
     * @return booleab (Si la imagen se creo)
     * @throws IOException
     */
    public boolean byteToImage(String path, int width, int height, int[] data) throws IOException{
        try {
            MemoryImageSource mis = new MemoryImageSource(width,height,data,0,width);
            Image im = Toolkit.getDefaultToolkit().createImage(mis);

            BufferedImage buffImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
            buffImage.getGraphics().drawImage(im,0,0,null);
            ImageIO.write(buffImage, "png", new File(path)); // Esto lanza  IOException

        } catch(AWTError awtError){
            System.out.print("An error loading assistive technologies happened.");
        } catch(Exception e){
            System.out.println("An unexpected error occurred.");
        }
        return false;    
    }
}
