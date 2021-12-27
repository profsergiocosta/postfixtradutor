package br.ufma.ecp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Hello world!
 *
 */

 
 
 class Parser {
     private byte[] input;
     private int current; 
     public Parser (byte[] input) {
         this.input = input;
     }

     public void parse () {
         expr();
     }

     private void expr() {
         term();
         while (peek() != 0) {
             if (peek() == '+') {
                 match('+');
                 term();
                 System.out.println("add");
             }else  if (peek() == '-') {
                match('-');
                term();
                System.out.println("sub");
             }else return;
         }
     }

     private void term () {
         if (Character.isDigit(peek())) {
             System.out.println(peek());
             match(peek());
         } else {
            throw new Error("syntax error");
         }
     }

     private char peek () {
         if (current < input.length)
            return (char)input[current];
        return 0;
     }

     private void match (char c) {
         if (c == peek()) {
             current++;
         } else {
             throw new Error("syntax error");
         }
     }
 }

public class App 
{

    private static String fromFile() {
        File file = new File("main.jack");

        byte[] bytes;
        try {
            bytes = Files.readAllBytes(file.toPath());
            String textoDoArquivo = new String(bytes, "UTF-8");
            return textoDoArquivo;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    } 
    public static void main( String[] args )
    {
        String input = "8+5-7+9";
        Parser p = new Parser (input.getBytes());
        p.parse();
   
    }
}
