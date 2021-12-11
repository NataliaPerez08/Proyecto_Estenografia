package main.java.md;
public class Steganography{

    public int[] hideMessage(int[] image, String message){
        int[] newImg = new int[image.length]; 
        String noSpaceMsg = message.replaceAll("\\s", "");
        int j=0;
        int k=0;
        for (int i : image) {
            Long tmp;
            String biString = Integer.toBinaryString(i);
            int len = biString.length();
            if(k < noSpaceMsg.length()){
                String modifedString = noSpaceMsg.charAt(k++)+biString.substring(0, len-1);//+noSpaceMsg.charAt(k++);
                tmp = Long.parseLong(modifedString, 2);
            }
            tmp = Long.parseLong(biString, 2);
            newImg[j++] = tmp.intValue();
        }
        return newImg;
    }
    public String getMessage(int[] image) {
        String msgBin="";
        int c=0;
        for (int i : image) {
            String biString = Integer.toBinaryString(i);
            int len = biString.length()-1;
            msgBin += biString.charAt(len);
            if(c++ == 8){
                msgBin+=" ";
                c=0;
            }
        }
        return msgBin;
    }
}