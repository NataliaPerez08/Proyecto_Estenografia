package main.java.md;
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


    public int[] imageToBinary(){
        return null;
    }

   
}
