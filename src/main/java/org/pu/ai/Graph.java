package org.pu.ai;

import org.pu.ai.model.Link;
import org.pu.ai.model.Node;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {

    private HashMap<String, Node> myGraph = new HashMap<>();

    public Node getNode(String name) {
        return myGraph.get(name);
    }

    public void addNode(String name) {
        Node node = new Node(name);
        myGraph.put(name, node);
    }

    public void addNode(Node node) {
        myGraph.put(node.getName(), node);
    }

    public void addLink(String nameOne, String nameTwo, boolean isTwoWay) {
        if(!myGraph.containsKey(nameOne) || !myGraph.containsKey(nameTwo)) {
            System.out.println("Wrong or missing node name!");
            return;
        }

        Node nodeOne = myGraph.get(nameOne);
        ArrayList<Link> linksOne = nodeOne.getLinks();
        linksOne.add(new Link(nameTwo,0));
        //myGraph.get(nameOne).getLinks().add(new Link(nameTwo,0));

        if(isTwoWay) {
            myGraph.get(nameTwo).getLinks().add(new Link(nameOne,0));
        }

    }//end addLink

    public void graphReset() {
        for(Node node : myGraph.values()) {
            node.reset();
        }
    }

}//end class Graph
