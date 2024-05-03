import java.util.ArrayList;

public class Tree {
    public int g,h;
    public String kata;
    public ArrayList<String> path;

    public Tree(int g,int h, String kata, ArrayList<String> path){
        this.g = g;
        this.h = h;
        this.kata = kata;
        this.path = path;
    }
}
