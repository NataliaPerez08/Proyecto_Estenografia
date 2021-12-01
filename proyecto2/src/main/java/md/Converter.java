package main.java.md;
public class Converter{

    public String binaryToText(StringBuilder text){
        String str = text.toString();
        int charCode = Integer.parseInt(info, 2);
        return "";
    }

    public int[] imageToBinary(){
        return null;
    }

    public StringBuilder textToBinary(String text){ 
        byte[] bytes = text.getBytes();
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
        return binary;
    }
}
