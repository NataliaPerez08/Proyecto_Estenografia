package main.java.md;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
public class Converter{

	/**  Este método recibe una cadena de caracteres en binario y
	 * lo cambia letras
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

	/** Este método recibe un texto y lo pasa a binario */
	public String textToBinary(String text){ 
		byte[] bytes = text.getBytes();
		return byteToBinary(bytes);
	}

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

	public int[] imageToBinary(BufferedImage image){
		int width = image.getWidth();
		int height = image.getHeight();
		int[] pixels = new int[width * height];

		// Retrieve pixel info and store in 'pixels' variable
		PixelGrabber pgb = new PixelGrabber(image, 0, 0, width, height, pixels, 0, width);
		try{
			pgb.grabPixels();
			return pixels;
		}catch(Exception e){
			System.out.print(e);
		}
		return null;
	}

	public boolean byteToImage(String path, int width, int height, int[] data) throws IOException{
		try{
			MemoryImageSource mis = new MemoryImageSource(width,height,data,0,width);
			Image im = Toolkit.getDefaultToolkit().createImage(mis);

			BufferedImage buffImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
			buffImage.getGraphics().drawImage(im,0,0,null);
			ImageIO.write(buffImage, "png", new File(path));
		}catch(Exception e){System.out.print(e);}
		return false;    
	}
}
