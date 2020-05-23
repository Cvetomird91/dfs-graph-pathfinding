package org.pu.ai.model;

import java.util.ArrayList;

public class Node {

    private String name; //A
    private int weight,x,y;
    private ArrayList<Link> links = new ArrayList<>();

    private boolean visited = false;
    private int depth = 0;
    Node parent = null;

    public Node(String n) {
        this.name = n;
    }

    public Node(String n, int w) {
        this.name = n;
        this.weight = w;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Link> getLinks() {
        return links;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void reset() {
        visited = false;
        depth = 0;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getWeight() {
        return weight;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return name + " => " + weight;
    }

}
