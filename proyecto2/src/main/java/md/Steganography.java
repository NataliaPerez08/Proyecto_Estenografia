package main.java.md;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.awt.image.BufferedImage;

import main.java.md.Converter;

public class Steganography{
	Converter cv = new Converter();

	public int[] hideMessage(BufferedImage img, String message){        
		int[] pixels = cv.imageToBinary(img);
		int[] newImg = Arrays.copyOf(pixels,pixels.length);
		String[] charBinStrings = message.split(" ");
		int len = charBinStrings.length/2;
		for(int k=0; k < len; k++){
			String tmp = charBinStrings[k];
			int lenChar = tmp.length();
			String firstHalf = tmp.substring(0, lenChar/2);
			String secondHalf = tmp.substring(lenChar/2,lenChar);
			int edit = handlesinglepixel(pixels[k], firstHalf);
			int edit2 = handlesinglepixel(pixels[k+1], secondHalf);
			newImg[k] = edit;
			newImg[k+1] = edit2;
		}
		return newImg;
	}

	public int handlesinglepixel(int pixel, String half) {
		String newNumber =chageLSB(pixel, half);
		return Integer.parseInt(newNumber,2);
	}

	public String chageLSB(int pixel,String binString){
		int alpha = (pixel >> 24) & 0xff;
		int red   = (pixel >> 16) & 0xff;
		int green = (pixel >>  8) & 0xff;
		int blue  = (pixel      ) & 0xff;
		String alphaString = Integer.toBinaryString(alpha);
		String redString = Integer.toBinaryString(red);
		String greenString = Integer.toBinaryString(green);
		String blueString = Integer.toBinaryString(blue);
		if(binString.length() == 4){
			alphaString = appendBin(alphaString, binString.charAt(0));
			redString = appendBin(redString, binString.charAt(1));
			greenString = appendBin(greenString, binString.charAt(2));
			blueString = appendBin(blueString, binString.charAt(3));
		}
		return alphaString+redString+greenString+blueString;
	}

	public String appendBin(String bin, char c){
		String auxBin="";
		auxBin = bin.substring(0,bin.length()-1)+c;
		return auxBin;
	}

	public String getMessage(int[] image) {
		String aux="";
		StringBuilder msg = new StringBuilder();

		int len = image.length;
		for (int i = 0; i < len/2; i++) {
			aux += handleTwopixel(i,i+1);
			if(aux.length() == 8){
				int tmp = Integer.valueOf(aux,2);
				String slp = validChar((char) tmp);
				msg.append(slp);
				aux = "";
			}
		}
		return msg.toString();
	}

	public String validChar(char c){
		if (c >= 65 && c <= 90){
			return c+"";
		}
		return "";
	}

	public String handleTwopixel(int pixel,int pixel2) {
		return handlesinglepixel(pixel)+handlesinglepixel(pixel2);
	}

	public String handlesinglepixel(int pixel) {
		int alpha = (pixel >> 24) & 0xff;
		int red   = (pixel >> 16) & 0xff;
		int green = (pixel >>  8) & 0xff;
		int blue  = (pixel      ) & 0xff;
		String alphaString = Integer.toBinaryString(alpha);
		String redString = Integer.toBinaryString(red);
		String greenString = Integer.toBinaryString(green);
		String blueString = Integer.toBinaryString(blue);

		String a = appendBin(alphaString);
		String r = appendBin(redString);
		String g = appendBin(greenString);
		String b = appendBin(blueString);
		return a+r+g+b;
	}

	public String appendBin(String bin){
		String auxBin="";
		auxBin = bin.charAt(bin.length()-1)+"";
		return auxBin;
	}
}
