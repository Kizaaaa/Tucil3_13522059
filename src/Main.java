import java.util.Scanner;
import java.util.Set;

public class Main {
    public static Set<String> Dictionary;

    public static boolean isWordValid(String s){
        if(s.indexOf(" ") != -1){
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        

        System.out.print("Masukkan Kata Awal : ");
        String start = sc.nextLine();
        while(!isWordValid(start)){
            System.out.print("\nKata tidak valid!\n\nMasukkan Kata Awal : ");
            start = sc.nextLine();
        }

        System.out.print("Masukkan Kata Akhir : ");
        String end = sc.nextLine();
        while(!isWordValid(end) && start.length() != end.length()){
            if(start.length() != end.length()){
                System.out.println("\nPanjang kata berbeda!");
            } else {
                System.out.println("\nKata tidak valid!");
            }
            System.out.print("\nMasukkan Kata Akhir : ");
            end = sc.nextLine();
        }

        

        sc.close();
    }
}