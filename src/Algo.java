import java.io.*;
import java.util.*;

public class Algo {
    public static Set<String> dictionary, visited = new HashSet<>();
    public static String start, end;
    public static long startTime, endTime, dikunjungi;
    public static int len;
    public static ArrayList<String> path;
    public static ArrayList<Node> queue = new ArrayList<>();
    

    // Membaca file txt yang berisi kata inggris yang valid
    public static void readDictionary(String filePath) {
        dictionary = new HashSet<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    public static void UCS(){
        // Membersihkan visited, queue dan dikunjungi untuk GUI
        visited.clear();
        queue.clear();
        dikunjungi = 0;
        len = start.length();
        len = start.length();
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

    public static void insertGBFS(Node t){
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

    public static void delete(String s){
        for(int i=0;i<queue.size();i++){
            if(queue.get(i).kata.equals(s)){
                queue.remove(i);
                break;
            }
        }
    }

    public static void GBFS(){
        // Membersihkan visited, queue dan dikunjungi untuk GUI
        visited.clear();
        queue.clear();
        dikunjungi = 0;
        len = start.length();
        // Memulai perhitungan waktu
        startTime = System.currentTimeMillis();

        // Deklarasi node awal
        Node t = new Node(0, calculateDistanceToFinish(start), start, new ArrayList<String>());

        // Memasukkan node awal pada queue
        queue.add(t);
        visited.add(start);

        // Memulai proses GBFS
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
                            insertGBFS(tempTree);
                            visited.add(tempString);
                        }
                    }
                }
            }

            // Menghapus kata yang sudah dicek dari queue
            delete(treeSekarang.kata);
        }

        // Menghentikan perhitungan waktu
        endTime = System.currentTimeMillis();

        // Mencatat jalur yang telah ditemukan
        path = queue.get(0).path;
    }

    public static void insertAS(Node t){
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
        // Membersihkan visited, queue dan dikunjungi untuk GUI
        visited.clear();
        queue.clear();
        dikunjungi = 0;
        len = start.length();
        // Memulai perhitungan waktu
        startTime = System.currentTimeMillis();

        // Deklarasi node awal
        Node t = new Node(0, calculateDistanceToFinish(start), start, new ArrayList<String>());

        // Memasukkan node awal pada queue
        queue.add(t);
        visited.add(start);

        // Memulai proses AS
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
                            insertAS(tempTree);
                            visited.add(tempString);
                        }
                    }
                }
            }

            // Menghapus kata yang sudah dicek dari queue
            delete(treeSekarang.kata);
        }

        // Menghentikan perhitungan waktu
        endTime = System.currentTimeMillis();

        // Mencatat jalur yang telah ditemukan
        path = queue.get(0).path;
    }
}