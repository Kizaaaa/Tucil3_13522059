import java.util.ArrayList;

public class Tree {
    public int cost;
    public String kata;
    public ArrayList<String> path;

    public Tree(int cost, String kata, ArrayList<String> path){
        this.cost = cost;
        this.kata = kata;
        this.path = path;
    }
}
