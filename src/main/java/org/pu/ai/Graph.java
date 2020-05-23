package org.pu.ai;

import org.pu.ai.model.Link;
import org.pu.ai.model.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public void addLink(String nameOne, String nameTwo, boolean isTwoWay, int length) {
        if(!myGraph.containsKey(nameOne) || !myGraph.containsKey(nameTwo)) {
            System.out.println("Wrong or missing node name!");
            return;
        }

        Node nodeOne = myGraph.get(nameOne);
        ArrayList<Link> linksOne = nodeOne.getLinks();
        linksOne.add(new Link(nameTwo, length));
        //myGraph.get(nameOne).getLinks().add(new Link(nameTwo,0));

        if(isTwoWay) {
            myGraph.get(nameTwo).getLinks().add(new Link(nameOne, length));
        }

    }//end addLink

    public void graphReset() {
        for(Node node : myGraph.values()) {
            node.reset();
        }
    }

    public List<Link> getLink(String name) {
        return myGraph.get(name).getLinks();
    }

    public void readData(String linksFilePath, String nodesFilePath) throws IOException {
        BufferedReader linkReader = new BufferedReader(new FileReader(linksFilePath));
        BufferedReader nodeReader = new BufferedReader(new FileReader(nodesFilePath));

        //retrieve and assign node data
        String nodeRow;
        while ((nodeRow = nodeReader.readLine()) != null) {
            // remove trailing semi - colon at the end of the line
            String line = nodeRow.substring(0, nodeRow.length() - 1);
            String[] data = line.split(", ");
            Node node = new Node(data[0], Integer.parseInt(data[1]));
            addNode(node);
        }

        //retrieve and assign link data
        String linkRow;
        while ((linkRow = linkReader.readLine()) != null) {
            // remove trailing semi - colon at the end of the line
            String line = linkRow.substring(0, linkRow.length() - 1);
            String[] data = line.split(", ");
            addLink(data[0], data[1], Boolean.parseBoolean(data[2]), Integer.parseInt(data[3]));
        }

        linkReader.close();
        nodeReader.close();
    }

}//end class Graph
