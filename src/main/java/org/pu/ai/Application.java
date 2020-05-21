package org.pu.ai;

import org.pu.ai.model.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Application {

    static Graph graph = new Graph();

    public static void init(Graph g) throws IOException {
        readData(g);
    }

    private static void readData(Graph graph) throws IOException {
        BufferedReader linkReader = new BufferedReader(new FileReader("src/resources/links.grf"));
        BufferedReader nodeReader = new BufferedReader(new FileReader("src/resources/nodes.grf"));

        //retrieve and assign node data
        String nodeRow;
        while ((nodeRow = nodeReader.readLine()) != null) {
            // remove trailing semi - colon at the end of the line
            String line = nodeRow.substring(0, nodeRow.length() - 1);
            String[] data = line.split(", ");
            Node node = new Node(data[0], Integer.parseInt(data[1]));
            graph.addNode(node);
        }

        //retrieve and assign link data
        String linkRow;
        while ((linkRow = linkReader.readLine()) != null) {
            // remove trailing semi - colon at the end of the line
            String line = linkRow.substring(0, linkRow.length() - 1);
            String[] data = line.split(", ");
            graph.addLink(data[0], data[1], Boolean.parseBoolean(data[2]), Integer.parseInt(data[3]));
        }

        linkReader.close();
        nodeReader.close();
    }

    public static void main(String[] args) throws IOException{
        init(graph);
    }

}
