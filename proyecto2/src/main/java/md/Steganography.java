package main.java.md;
public class Steganography{

    public int[] hideMessage(int[] image, String message){
        int[] newImg = new int[image.length];

        int j=0;
        for (int i : image) {
            String biString = Integer.toBinaryString(i);
            Long tmp = Long.parseLong(biString, 2);
            newImg[j++] = tmp.intValue();
        }
        return newImg;
    }

    public void getMessage(int[] bitmap) {
    }
}