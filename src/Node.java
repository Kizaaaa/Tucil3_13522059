import java.util.ArrayList;

public class Node {
    public int g,h;
    public String kata;
    public ArrayList<String> path;

    public Node(int g,int h, String kata, ArrayList<String> path){
        this.g = g;
        this.h = h;
        this.kata = kata;
        this.path = path;
    }
}
