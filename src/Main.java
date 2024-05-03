import java.io.*;
import java.util.*;

public class Main {
    public static Set<String> dictionary, visited;
    public static String start, end;
    public static long startTime, endTime, dikunjungi = 0;
    public static int len;
    public static ArrayList<String> path;
    public static ArrayList<Node> queue = new ArrayList<>();;
    

    // Membaca file txt yang berisi kata inggris yang valid
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

    public static void UCS(){
        // Memulai perhitungan waktu
        startTime = System.currentTimeMillis();

        // Deklarasi node awal
        Node t = new Node(0,0, start, new ArrayList<String>());

        // Memasukkan node awal pada queue
        queue.add(t);
        visited.add(start);

        // Memulai proses UCS
        while(true){
            // Menambah node yang dikunjungi
            dikunjungi++;

            // Kata tujuan ditemukan! keluar dari while loop
            if(queue.get(0).kata.equals(end)){
                break;
            }

            // Menghasilkan kata yang berbeda 1 huruf
            for(int i=0;i<len;i++){
                for(char c : "abcdefghijklmnopqrstuvwxyz".toCharArray()){
                    if(queue.get(0).kata.charAt(i) != c){
                        String tempString = queue.get(0).kata.substring(0, i) + c + queue.get(0).kata.substring(i+1);
                        // Jika kata valid dan belum dilewati
                        if(!visited.contains(tempString) && dictionary.contains(tempString)){
                            ArrayList<String> tempPath = new ArrayList<>(queue.get(0).path);
                            tempPath.add(tempString);
                            Node tempTree = new Node(queue.get(0).g+1,0, tempString, tempPath);
                            queue.add(tempTree);
                            visited.add(tempString);
                        }
                    }
                }
            }
            
            // Menghapus kata yang sudah dicek dari queue
            queue.remove(0);
        }

        // Menghentikan perhitungan waktu
        endTime = System.currentTimeMillis();

        // Mencatat jalur yang telah ditemukan
        path = queue.get(0).path;
    }

    public static int calculateDistanceToFinish(String s){
        int ret = 0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) != end.charAt(i)){
                ret++;
            }
        }
        return ret;
    }

    public static void InsertGBFS(Node t){
        if(queue.isEmpty() || queue.get(queue.size()-1).h <= t.h){
            queue.add(t);
        } else {
            for(int i=0;i<queue.size();i++){
                if(queue.get(i).h > t.h){
                    queue.add(i, t);
                    break;
                }
            }
        }
    }

    public static void Delete(String s){
        for(int i=0;i<queue.size();i++){
            if(queue.get(i).kata.equals(s)){
                queue.remove(i);
                break;
            }
        }
    }

    public static void GBFS(){
        // Memulai perhitungan waktu
        startTime = System.currentTimeMillis();

        // Deklarasi node awal
        Node t = new Node(0, calculateDistanceToFinish(start), start, new ArrayList<String>());

        // Memasukkan node awal pada queue
        queue.add(t);
        visited.add(start);

        // Memulai proses UCS
        while(true){
            // Menambah node yang dikunjungi
            dikunjungi++;
            Node treeSekarang = queue.get(0);

            // Kata tujuan ditemukan! keluar dari while loop
            if(treeSekarang.kata.equals(end)){
                break;
            }

            // Menghasilkan kata yang berbeda 1 huruf
            for(int i=0;i<len;i++){
                for(char c : "abcdefghijklmnopqrstuvwxyz".toCharArray()){
                    if(treeSekarang.kata.charAt(i) != c){
                        String tempString = treeSekarang.kata.substring(0, i) + c + treeSekarang.kata.substring(i+1);
                        // Jika kata valid dan belum dilewati
                        if(!visited.contains(tempString) && dictionary.contains(tempString)){
                            ArrayList<String> tempPath = new ArrayList<>(treeSekarang.path);
                            tempPath.add(tempString);
                            Node tempTree = new Node(0,calculateDistanceToFinish(tempString), tempString, tempPath);
                            InsertGBFS(tempTree);
                            visited.add(tempString);
                        }
                    }
                }
            }

            // Menghapus kata yang sudah dicek dari queue
            Delete(treeSekarang.kata);
        }

        // Menghentikan perhitungan waktu
        endTime = System.currentTimeMillis();

        // Mencatat jalur yang telah ditemukan
        path = queue.get(0).path;
    }

    public static void InsertAS(Node t){
        if(queue.isEmpty() || queue.get(queue.size()-1).h + queue.get(queue.size()-1).g <= t.h + t.g){
            queue.add(t);
        } else {
            for(int i=0;i<queue.size();i++){
                if(queue.get(i).h + queue.get(i).g > t.h + t.g){
                    queue.add(i, t);
                    break;
                }
            }
        }
    }

    public static void AS(){
        // Memulai perhitungan waktu
        startTime = System.currentTimeMillis();

        // Deklarasi node awal
        Node t = new Node(0, calculateDistanceToFinish(start), start, new ArrayList<String>());

        // Memasukkan node awal pada queue
        queue.add(t);
        visited.add(start);

        // Memulai proses UCS
        while(true){
            // Menambah node yang dikunjungi
            dikunjungi++;
            Node treeSekarang = queue.get(0);

            // Kata tujuan ditemukan! keluar dari while loop
            if(treeSekarang.kata.equals(end)){
                break;
            }

            // Menghasilkan kata yang berbeda 1 huruf
            for(int i=0;i<len;i++){
                for(char c : "abcdefghijklmnopqrstuvwxyz".toCharArray()){
                    if(treeSekarang.kata.charAt(i) != c){
                        String tempString = treeSekarang.kata.substring(0, i) + c + treeSekarang.kata.substring(i+1);
                        // Jika kata valid dan belum dilewati
                        if(!visited.contains(tempString) && dictionary.contains(tempString)){
                            ArrayList<String> tempPath = new ArrayList<>(treeSekarang.path);
                            tempPath.add(tempString);
                            Node tempTree = new Node(treeSekarang.g+1,calculateDistanceToFinish(tempString), tempString, tempPath);
                            InsertAS(tempTree);
                            visited.add(tempString);
                        }
                    }
                }
            }

            // Menghapus kata yang sudah dicek dari queue
            Delete(treeSekarang.kata);
        }

        // Menghentikan perhitungan waktu
        endTime = System.currentTimeMillis();

        // Mencatat jalur yang telah ditemukan
        path = queue.get(0).path;
    }
    
    public static void main(String[] args) {
        // Deklarasi set untuk menyimpan kata inggris yang valid
        readDictionary("../src/dict.txt");

        // Deklarasi set untuk menyimpan kata yang sudah dicek
        visited = new HashSet<String>();

        // Deklarasi Scanner untuk input
        Scanner sc = new Scanner(System.in);
        
        // Input dan validasi kata awal
        System.out.print("Masukkan Kata Awal : ");
        start = sc.nextLine().toLowerCase();
        while(!dictionary.contains(start)){
            System.out.print("\nKata tidak valid!\n\nMasukkan Kata Awal : ");
            start = sc.nextLine().toLowerCase();
        }

        // Input dan validasi kata akhir
        System.out.print("Masukkan Kata Akhir : ");
        end = sc.nextLine().toLowerCase();
        while(!dictionary.contains(end) || start.length() != end.length()){
            if(start.length() != end.length()){
                System.out.println("\nPanjang kata berbeda!");
            } else {
                System.out.println("\nKata tidak valid!");
            }
            System.out.print("\nMasukkan Kata Akhir : ");
            end = sc.nextLine().toLowerCase();
        }

        // Menyimpan panjang kata
        len = end.length();

        // Pemilihan algoritma
        System.out.print("Algoritma :\n1. Uniform Cost Search (UCS)\n2. Greedy Best-First Search\n3. A* Search\nAlgoritma Yang digunakan(1/2/3) : ");
        String algo = sc.nextLine();
        while(!(algo != "1" || algo != "2" || algo != "3")){
            System.out.print("\ninput tidak valid!\nAlgoritma :\n1. Uniform Cost Search (UCS)\n2. Greedy Best-First Search\n3. A* Search\nAlgoritma Yang digunakan(1/2/3) : ");
            algo = sc.nextLine();
        }

        // Close Scanner
        sc.close();

        
        try {
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

            // Print Path
            System.out.println("\nPath :");
            System.out.println(start.toUpperCase());
            if(start != end){
                for(String s : path){
                    System.out.println(s.toUpperCase());
                }
            }

            // Print node yang dikunjungi dan waktu yang diperlukan
            System.out.println("\nPanjang path : " + path.size() + " Langkah\nNode yang dikunjungi : " + dikunjungi + "\nWaktu : " + (endTime-startTime) + " ms");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Tidak ditemukan path dari " + start + " menuju " + end + ".");
        }        
    }
}