package org.pu.ai;

import org.pu.ai.model.Link;
import org.pu.ai.model.Node;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearch implements Searchable {

    private Graph graph;

    public DepthFirstSearch(Graph graph) {
        this.graph = graph;
    }

    @Override
    public boolean search(String nameFirst, String nameSecond) {

        graph.graphReset();
        Node nodeOne = graph.getNode(nameFirst);
        Node nodeTwo = graph.getNode(nameSecond);
        List<Node> pathList = new ArrayList<>();

        if(nodeOne == null || nodeTwo == null) {
            System.out.println("Wrong or missing node!");
            return false;
        }

        pathList.add(nodeOne);
        nodeOne.setVisited(true);

        searchHelper(nodeOne, nodeTwo, pathList);

        return false;
    }

    private void searchHelper(Node source, Node destination,
                              List<Node> localPathList) {

        source.setVisited(true);

        if (source == destination) {
            System.out.println(localPathList);
            source.setVisited(false);
            return;
        }

        for (Link link : source.getLinks()) {
            Node currentNode = graph.getNode(link.getRelatedName());

            if (!currentNode.isVisited()) {
                localPathList.add(currentNode);

                searchHelper(currentNode, destination, localPathList);

                localPathList.remove(currentNode);
            }
        }
    }

}
