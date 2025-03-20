import java.io.*;
public class Laborator1 {
    public static void main(String[] args) throws Exception {

        File file = new File("D:\\PP\\lab1ex2\\text.txt");
//Citeste continutul fisierului
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st = br.readLine();
        System.out.println(st);
        eliminarepunctuatie(st);
        SchimbareLitere(st);
    }

    public static void eliminarepunctuatie(String str) {
        //inlocuieste punctuatia cu un spatiu
        String result = str.replaceAll("\\p{Punct}", "");
        System.out.println(result);// afiseaza sirul
    }
//Schimba literele mici in litere mari
    public static void SchimbareLitere(String str) {

        StringBuffer newStr = new StringBuffer(str);

        for (int i = 0; i < str.length(); i++) {

            if (Character.isLowerCase(str.charAt(i))) {//transforma in litera mare
                newStr.setCharAt(i, Character.toUpperCase(str.charAt(i)));
            }
            else if (Character.isUpperCase(str.charAt(i))) {
                newStr.setCharAt(i, Character.toLowerCase(str.charAt(i)));
            }
        }
        System.out.println("Textul dupa schimbarea literelor mari cu litere mici si a celor mici cu litere mari: " + newStr);
    }
}