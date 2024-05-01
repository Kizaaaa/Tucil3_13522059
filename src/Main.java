import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static Set<String> Dictionary;

    public static void readDictionary(String filePath) {
        Dictionary = new HashSet<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Dictionary.add(line.trim()); // trim() to remove any leading or trailing whitespaces
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        // Deklarasi set untuk menyimpan kata inggris yang valid
        readDictionary("dict.txt");

        // Deklarasi Scanner untuk input
        Scanner sc = new Scanner(System.in);
        
        // Input dan validasi kata awal
        System.out.print("Masukkan Kata Awal : ");
        String start = sc.nextLine();
        while(!Dictionary.contains(start)){
            System.out.print("\nKata tidak valid!\n\nMasukkan Kata Awal : ");
            start = sc.nextLine();
        }

        // Input dan validasi kata akhir
        System.out.print("Masukkan Kata Akhir : ");
        String end = sc.nextLine();
        while(!Dictionary.contains(end) || start.length() != end.length()){
            if(start.length() != end.length()){
                System.out.println("\nPanjang kata berbeda!");
            } else {
                System.out.println("\nKata tidak valid!");
            }
            System.out.print("\nMasukkan Kata Akhir : ");
            end = sc.nextLine();
        }

        
        // Close Scanner
        sc.close();
    }
}