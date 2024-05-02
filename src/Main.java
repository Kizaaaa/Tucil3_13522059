import java.io.*;
import java.util.*;

public class Main {
    public static Set<String> dictionary, visited;
    public static String start, end;
    public static long startTime, endTime;

    public static void readDictionary(String filePath) {
        dictionary = new HashSet<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    public static ArrayList<String> UCS(){
        // Memulai perhitungan waktu
        startTime = System.currentTimeMillis();

        // Deklarasi queue
        ArrayList<Tree> queue = new ArrayList<>();

        // Deklarasi node awal
        Tree t = new Tree(0, start, null);

        // Memasukkan node awal pada queue
        queue.add(t);

        // Memulai proses UCS
        while(true){
            // Kata tujuan ditemukan! keluar dari while loop
            if(queue.get(0).kata == end){
                break;
            }

            // Jika kata belum dikunjungi a
            if(!visited.contains(queue.get(0).kata)){

            }
            queue.remove(0);
        }

        // Menghentikan perhitungan waktu
        endTime = System.currentTimeMillis();

        // Mengembalikan jalur yang telah ditemukan
        return queue.get(0).path;
    }

    public static void GBFS(){
        // Memulai perhitungan waktu
        startTime = System.currentTimeMillis();

        // Menghentikan perhitungan waktu
        endTime = System.currentTimeMillis();
    }

    public static void AS(){
        // Memulai perhitungan waktu
        startTime = System.currentTimeMillis();

        // Menghentikan perhitungan waktu
        endTime = System.currentTimeMillis();
    }
    
    public static void main(String[] args) {
        // Deklarasi set untuk menyimpan kata inggris yang valid
        readDictionary("dict.txt");

        // Deklarasi set untuk menyimpan kata yang sudah dicek
        visited = new HashSet<String>();

        // Deklarasi Scanner untuk input
        Scanner sc = new Scanner(System.in);
        
        // Input dan validasi kata awal
        System.out.print("Masukkan Kata Awal : ");
        start = sc.nextLine();
        while(!dictionary.contains(start)){
            System.out.print("\nKata tidak valid!\n\nMasukkan Kata Awal : ");
            start = sc.nextLine();
        }

        // Input dan validasi kata akhir
        System.out.print("Masukkan Kata Akhir : ");
        end = sc.nextLine();
        while(!dictionary.contains(end) || start.length() != end.length()){
            if(start.length() != end.length()){
                System.out.println("\nPanjang kata berbeda!");
            } else {
                System.out.println("\nKata tidak valid!");
            }
            System.out.print("\nMasukkan Kata Akhir : ");
            end = sc.nextLine();
        }

        // Pemilihan algoritma
        System.out.print("Algoritma :\n1. Uniform Cost Search (UCS)\n2. Greedy Best-First Search\n3. A* Search\nAlgoritma Yang digunakan(1/2/3) :");
        String algo = sc.nextLine();
        while(algo != "1" && algo != "2" && algo != "3"){
            System.out.println("\ninput tidak valid!\nAlgoritma :\n1. Uniform Cost Search (UCS)\n2. Greedy Best-First Search\n3. A* Search\nAlgoritma Yang digunakan(1/2/3) :");
            algo = sc.nextLine();
        }

        // Pemanggilan fungsi algoritma sesuai input
        switch (algo) {
            case "1": // Uniform Cost Search (UCS)
                UCS();
                break;
            case "2": // Greedy Best-First Search
                GBFS();
                break;
            case "3": // A* Search
                AS();
                break;
        }

        // Close Scanner
        sc.close();
    }
}